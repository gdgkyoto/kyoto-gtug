/*
 *    Copyright (C) 2007-2009 Aike J Sommer
 *
 *
 *    This program is free software: you can
 *    redistribute it and/or modify it under the terms of the GNU
 *    General Public License as published by the Free Software
 *    Foundation, either version 3 of the License, or (at your
 *    option) any later version.
 *
 *    This program is distributed in the hope
 *    that it will be useful, but WITHOUT ANY WARRANTY; without even
 *    the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *    PARTICULAR PURPOSE.  See the GNU General Public License for
 *    more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see
 *    <http://www.gnu.org/licenses/>.
 *
 *
 *    You can reach the author and get more information about this
 *    project at: http://aikesommer.name/ or
 *    http://code.google.com/p/viewncontrol
 */
package name.aikesommer.viewncontrol.protocol;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.imageio.ImageIO;

/**
 * TODO:
 * - Optimierungen:
 *   - keep alive connections
 *   - pipelining??
 *   - bilder kombinieren, mit cliping
 *   - cursor shapes cachen
 *   - browser cache "aktivieren"
 * - Input:
 *   - div über den bildern, onclick
 * @author Aike J Sommer
 */
public class VNCSession {

    private static long NEXT_SESSION_KEY = new Date().getTime();

    public static final int SECURITY_VNC = 0x2;
    public static final int ENCODING_TIGHT = 0x7;
    public static final int ENCODING_TIGHT_OPTION_COMPRESSION = 0xffffff00;
    public static final int ENCODING_TIGHT_OPTION_QUALITY = 0xffffffe0;
    public static final int ENCODING_TIGHT_DATA = 0x0;
    public static final int ENCODING_TIGHT_FILTER = 0x4;
    public static final int ENCODING_TIGHT_FILL = 0x8;
    public static final int ENCODING_TIGHT_JPEG = 0x9;
    public static final int ENCODING_TIGHT_ZLIB_STREAM_MASK = 0x3;
    public static final int ENCODING_TIGHT_NODATA_MASK = 0x8;
    public static final int ENCODING_TIGHT_FILTER_COPY = 0x0;
    public static final int ENCODING_TIGHT_FILTER_PALETTE = 0x1;
    public static final int ENCODING_TIGHT_FILTER_GRADIENT = 0x2;
    public static final int ENCODING_RICHCURSOR = 0xFFFFFF11;
    public static final int ENCODING_POINTERPOS = 0xFFFFFF18;
    public static final int MESSAGE_FRAMEBUFFER_UPDATE = 0x0;
    public static final int MESSAGE_CUT_TEXT = 0x3;
    private Socket socket;
    private InputStream in;
    private boolean connected;
    private PixelFormat pixelFormat;
    private Inflater[] tightInflater;
    private String name;
    private int framebufferWidth;
    private int framebufferHeight;
    private boolean incrementalUpdate;
    private boolean updateRequested;
    private String sessionKey;
//    private int pointerPosX;
//    private int pointerPosY;
//    private int pointerOffsetX;
//    private int pointerOffsetY;
    private InetAddress host;
    private int port;
    private String secret;
    private SliceStore store;

    public VNCSession(InetAddress host, int port, String secret,
            SliceStore store) {
        this.host = host;
        this.port = port;
        this.secret = secret;
        this.store = store;
        reset();
    }

    private void reset() {
        socket = null;
        in = null;
        connected = false;
        pixelFormat = null;
        tightInflater = new Inflater[4];
        name = null;
        framebufferWidth = 0;
        framebufferHeight = 0;
        incrementalUpdate = false;
        updateRequested = false;
        sessionKey = "" + (NEXT_SESSION_KEY++);
//        pointerPosX = 0;
//        pointerPosY = 0;
//        pointerOffsetX = 0;
//        pointerOffsetY = 0;
        store.clear();
    }

    public void requestFullUpdate() throws IOException {
        incrementalUpdate = false;
        updateRequested = false;
        requestUpdate();
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public boolean isConnected() {
        return connected;
    }

    public String getName() {
        return name;
    }

    public int getFramebufferHeight() {
        return framebufferHeight;
    }

    public int getFramebufferWidth() {
        return framebufferWidth;
    }

    public void connect() throws IOException, VNCException.AuthenticationFailed {
        if (isConnected()) {
            throw new IllegalStateException();
        }

        System.out.println("vnc: trying to open " + host + ":" + port);
        socket = new Socket(host, port);
        in = new BufferedInputStream(socket.getInputStream());

        String versionOffer = new String(read(12));
        System.out.println("version offered: " + versionOffer.trim());
        int versionMajor = Integer.parseInt(versionOffer.substring(6, 7));
        int versionMinor = Integer.parseInt(versionOffer.substring(10, 11));
        System.out.println("version: " + versionMajor + "." + versionMinor);

        String versionRequest = "RFB 003.008\n";
        System.out.println("requesting version: " + versionRequest.trim());
        write(versionRequest.getBytes());

        byte numSecTypes = read(1)[0];
        byte[] secTypes = read(numSecTypes);
        byte secType = -1;
        for (int i = 0; i < secTypes.length; i++) {
            byte type = secTypes[i];
            System.out.println("offered sec type: " + type);
            if (type == 1 || type == 2) {
                secType = type;
                break;
            }
        }
        switch (secType) {
            case SECURITY_VNC:
                write(2, 1);
                byte[] challenge = read(16);
                System.out.println("challenge: " + dump(challenge));

                byte[] secretBytes = secret.getBytes();
                swapBits(secretBytes);
                byte[] response;
                try {
                    SecretKey key = SecretKeyFactory.getInstance("DES").
                            generateSecret(new DESKeySpec(secretBytes));
                    Cipher ecipher = Cipher.getInstance("DES/ECB/NoPadding");
                    ecipher.init(Cipher.ENCRYPT_MODE, key);
                    response = ecipher.doFinal(challenge);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("sending response: " + write(response));
                break;
            default:
                throw new IllegalArgumentException(
                        "security types not supported");
        }

        int secResult = readInt(4);
        System.out.println("security response: " + secResult);

        connected = secResult == 0;
        if (!connected) {
            socket.close();
            socket = null;
            throw new VNCException.AuthenticationFailed();
        }

        System.out.println("sending client init: " + write(1, 1));
        framebufferWidth = readInt(2);
        framebufferHeight = readInt(2);
        System.out.println("framebuffer: " + framebufferWidth + "x" + framebufferHeight);
        pixelFormat = new PixelFormat(read(16));
        if (pixelFormat.getDepth() != 24) {
            throw new IllegalArgumentException("only depth 24 supported");
        }
        name = new String(read(readLong(4)), "ASCII");
        System.out.println("name: " + name);

        System.out.println("sending encodings request: " + write(
                new byte[]{2, 0, 0, /* num encodings */ 4}) + write(
                ENCODING_TIGHT, 4) + write(ENCODING_TIGHT_OPTION_QUALITY + 5, 4) + write(
                ENCODING_RICHCURSOR, 4) + write(ENCODING_POINTERPOS, 4));
    }

    public void close() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
            }
        }
        reset();
    }

    public void requestUpdate() throws IOException {
        if (updateRequested) {
            return;
        }

        System.out.println("sending update request: " + write(new byte[]{3, (byte) (incrementalUpdate
                    ? 1 : 0)}) + write(0, 2) + write(0, 2) + write(
                framebufferWidth, 2) + write(framebufferHeight, 2));
        incrementalUpdate = true;
        updateRequested = true;
    }

    public void checkForMessage(boolean wait, int timeout) throws VNCException.ConnectionLost {
        try {
            boolean available = in.available() >= 2;
            int previousTimeout = -1;
            if (!available) {
                requestUpdate();

                if (!wait) {
                    return;
                } else if (timeout > 0) {
                    previousTimeout = socket.getSoTimeout();
                    socket.setSoTimeout(timeout);
                }
            }
            byte[] message;
            try {
                message = read(2);
            } catch (SocketTimeoutException ex) {
                return;
            } finally {
                if (previousTimeout >= 0) {
                    socket.setSoTimeout(previousTimeout);
                }
            }
            System.out.println("incoming message: " + dump(message));
            switch (message[0]) {
                case MESSAGE_FRAMEBUFFER_UPDATE:
                    handleFramebufferUpdate();
                    break;
                case MESSAGE_CUT_TEXT:
                    handleCutText();
                    break;
                default:
                    throw new IllegalArgumentException("server sent message: " + dump(message));
            }
            requestUpdate();
        } catch (Exception ex) {
            close();
            throw new VNCException.ConnectionLost(ex);
        }
    }

    public void mouseChanged(int x, int y, Set<Integer> buttons) throws IOException {
//        System.out.println("cursor move: " + x + "," + y + " " + buttons.size());
        int buttonMask = 0;
        for (Integer button : buttons) {
            buttonMask |= (1 << (button - 1));
        }
        System.out.println("sending mouse change: " + write(5, 1) + write(buttonMask, 1)
                + write(x, 2) + write(y, 2));
    }

    public void keyChanged(int key, boolean pressed) throws IOException {
//        System.out.println("cursor move: " + x + "," + y + " " + buttons.size());
        if (key < 32) {
            key |= 0xff00;
        }
        System.out.println("sending key change: " + write(4, 1) + write(pressed ? 1 : 0, 1)
                + write(0, 2) + write(key, 4));
    }

    private void handleFramebufferUpdate() throws IOException {
        int numRects = readInt(2);
        for (int i = 0; i < numRects; i++) {
            int x = readInt(2);
            int y = readInt(2);
            int width = readInt(2);
            int height = readInt(2);
            int enc = readInt(4);

            System.out.println("update rectangle: (" + x + "x" + y + "+" + width + "+" + height
                    + ") type: " + enc);
            switch (enc) {
                case ENCODING_TIGHT:
                    handleTight(x, y, width, height, i);
                    break;
                case ENCODING_RICHCURSOR:
                    handleRichCursor(x, y, width, height, enc);
                    break;
                case ENCODING_POINTERPOS:
                    System.out.println("pointer pos: " + x + "x" + y);
//                    pointerPosX = x;
//                    pointerPosY = y;
                    store.store(SliceStore.SliceType.Cursor,
                            new SliceStore.NoDataSlice(x, y, width, height, new HashMap()));
                    break;
                default:
                    throw new IllegalArgumentException("unknown encoding: " + enc);
            }
        }
        updateRequested = false;
    }

    private void handleCutText() throws IOException {
        read(2);
        int length = readInt(4);
        String text = new String(read(length));
        System.out.println("server cut text: " + text);
    }

    private int rgbBytesToInt(byte[] bytes, int offset) {
        int pixel =
                (bytes[offset + 0] < 0 ? (256 + bytes[offset + 0]) : bytes[offset + 0]) << 16;
        pixel |=
                (bytes[offset + 1] < 0 ? (256 + bytes[offset + 1]) : bytes[offset + 1]) << 8;
        pixel |=
                (bytes[offset + 2] < 0 ? (256 + bytes[offset + 2]) : bytes[offset + 2]);
        return pixel;
    }

    private int[] rgbBytesToInts(byte[] bytes) {
        return rgbBytesToInts(bytes, false);
    }

    private int[] argbBytesToInts(byte[] bytes) {
        return rgbBytesToInts(bytes, true);
    }

    private int[] rgbBytesToInts(byte[] bytes, boolean alpha) {
        int pixelStride = alpha ? 4 : 3;
        int redOffset = pixelStride - 3;
        int greenOffset = redOffset + 1;
        int blueOffset = greenOffset + 1;
        int alphaOffset = 0;

        int[] ints = new int[bytes.length / pixelStride];
        for (int i = 0; i < ints.length; i++) {
            int pixelOffset = i * pixelStride;

            int pixel = 0;
            if (alpha) {
                pixel |= (bytes[pixelOffset + alphaOffset] < 0 ? (256 + bytes[pixelOffset
                        + alphaOffset]) : bytes[pixelOffset + alphaOffset]) << 24;
            }
            pixel |= (bytes[pixelOffset + redOffset] < 0 ? (256 + bytes[pixelOffset + redOffset]) : bytes[pixelOffset
                    + redOffset]) << 16;
            pixel |= (bytes[pixelOffset + greenOffset] < 0
                    ? (256 + bytes[pixelOffset + greenOffset]) : bytes[pixelOffset + greenOffset])
                    << 8;
            pixel |= (bytes[pixelOffset + blueOffset] < 0 ? (256 + bytes[pixelOffset + blueOffset]) : bytes[pixelOffset
                    + blueOffset]);
            ints[i] = pixel;
        }
        return ints;
    }

    private int[] rgbBytesToInts(byte[] bytes, int pixelStride, byte[] alpha,
            int alphaBits) {
        int redOffset = 0;
        int greenOffset = redOffset + 1;
        int blueOffset = greenOffset + 1;

        int[] ints = new int[bytes.length / pixelStride];
        int alphaPos = 0;
        for (int i = 0; i < ints.length; i++) {
            int pixelOffset = i * pixelStride;

            int pixel = 0;
            int pixelAlpha = 0;
            for (int j = 0; j < alphaBits; j++) {
                pixelAlpha <<= 1;
                byte alphaByte = alpha[alphaPos / 8];
                int relPos = alphaPos % 8;
                pixelAlpha |= (alphaByte >> (7 - relPos)) & 0x1;
                alphaPos++;
            }
            if (pixelAlpha != 0) {
                for (int j = alphaBits; j < 8; j++) {
                    pixelAlpha <<= 1;
                    pixelAlpha |= 0x1;
                }
            }
            pixel |= pixelAlpha << 24;
            pixel |= (bytes[pixelOffset + redOffset] < 0 ? (256 + bytes[pixelOffset + redOffset]) : bytes[pixelOffset
                    + redOffset]) << 16;
            pixel |= (bytes[pixelOffset + greenOffset] < 0
                    ? (256 + bytes[pixelOffset + greenOffset]) : bytes[pixelOffset + greenOffset])
                    << 8;
            pixel |= (bytes[pixelOffset + blueOffset] < 0 ? (256 + bytes[pixelOffset + blueOffset]) : bytes[pixelOffset
                    + blueOffset]);
            ints[i] = pixel;
        }
        return ints;
    }

    public static byte[] createJPEG(int width, int height, int[] raw) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, raw, 0, width);
//        encoder.encode(image);
        ImageIO.write(image, "JPEG", out);

        return out.toByteArray();
    }

//    private byte[] createJPEG(int width, int height, byte[] raw) throws IOException {
//        return createJPEG(width, height, rgbBytesToInts(raw));
//    }
//
//    private byte[] createPNG(int width, int height, byte[] raw, boolean alpha) throws IOException {
//        return createPNG(width, height, rgbBytesToInts(raw, alpha));
//    }
//
//    private byte[] createPNG(int width, int height, byte[] raw, int pixelStride,
//            byte[] alpha, int alphaBits) throws IOException {
//        return createPNG(width, height, rgbBytesToInts(raw, pixelStride, alpha,
//                alphaBits));
//    }
    public static byte[] createPNG(int width, int height, int[] raw) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, width, height, raw, 0, width);
        ImageIO.write(image, "PNG", out);

        return out.toByteArray();
    }

    private void handleRichCursor(int x, int y, int width, int height, int index) throws IOException {
        Map<String, Object> info = new HashMap();
//        String debugKey = Random.getRandomString(10);
//        info.put("debug-key", debugKey);
        info.put("encoding", "rich-cursor");

//        System.out.println("debug-key: " + debugKey);

        byte[] cursorRaw = read(pixelFormat.getBits() * width * height / 8);
        System.out.println("raw cursor first 64 bytes: " + dump(cursorRaw, 0, 64));
        byte[] cursorTransparency = read((width * height + 7) / 8);
        System.out.println("raw cursor transparency: " + dump(cursorTransparency));
        int[] raw = rgbBytesToInts(cursorRaw, 4, cursorTransparency, 1);
//        pointerOffsetX = x;
//        pointerOffsetY = y;
        store.store(SliceStore.SliceType.Cursor, new SliceStore.RawDataSlice(raw, true,
                x, y, width, height, info));
    }

    private void handleTight(int x, int y, int width, int height, int index) throws IOException {
        int tightMode = readInt(1);
        if ((tightMode & 0xf) != 0) {
            throw new UnsupportedOperationException();
        }
        tightMode >>= 4;

        boolean filter = (tightMode & ENCODING_TIGHT_FILTER) != 0;
        int zlibStream = tightMode & ENCODING_TIGHT_ZLIB_STREAM_MASK;
        tightMode = ((tightMode & ENCODING_TIGHT_NODATA_MASK) != 0) ? tightMode
                : 0;

        Map<String, Object> info = new HashMap();
//        String debugKey = Random.getRandomString(10);
//        info.put("debug-key", debugKey);
        info.put("encoding", "tight");
        info.put("mode", tightMode);

//        System.out.println("debug-key: " + debugKey);

        switch (tightMode) {
            case ENCODING_TIGHT_DATA: {
                boolean gradient = false;
                int[] palette = null;
                if (filter) {
                    System.out.println("tight filter!");
                    int filterType = readInt(1);
                    info.put("filter", filterType);

                    switch (filterType) {
                        case ENCODING_TIGHT_FILTER_GRADIENT:
                            System.out.println("filter: gradient");
                            gradient = true;
                            break;
                        case ENCODING_TIGHT_FILTER_PALETTE:
                            System.out.println("filter: palette");
                            int numColors = readInt(1) + 1;
                            byte[] paletteData = read(numColors * pixelFormat.getDepth() / 8);
                            palette = new int[numColors];
                            for (int i = 0; i < palette.length; i++) {
                                palette[i] = rgbBytesToInt(paletteData,
                                        pixelFormat.getDepth() * i / 8);
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("filter type: " + filterType);
                    }
                }
                System.out.println("tight data mode!");
                int pixelStride = (palette == null ? (pixelFormat.getDepth())
                        : (palette.length == 2 ? 1 : 8));
                int rowStride = (pixelStride * width + 7) / 8;
                int rawLength = rowStride * height;
                int fbLength = width * height;

                byte[] inflated;
                int inflatedSize;
                if (rawLength < 12) {
                    System.out.println("data length: " + rawLength);
                    inflated = read(rawLength);
                    inflatedSize = rawLength;
                } else {
                    int length = readTightLength();
                    System.out.println("data length: " + length + "/" + rawLength);
                    byte[] deflated = read(length);
                    inflated = new byte[rawLength];

                    if (tightInflater[zlibStream] == null) {
                        tightInflater[zlibStream] = new Inflater();
                    }
                    tightInflater[zlibStream].setInput(deflated);
                    try {
                        inflatedSize = tightInflater[zlibStream].inflate(
                                inflated);
                    } catch (DataFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (width <= 2 && height <= 2) {
//                        Log.debugS("ignoring slice because its too small!");
                    break;
                }

                if (gradient) {
                    applyGradient(inflated, 3, 3 * width, height);
                }

                int[] fbData;
                if (palette != null) {
                    fbData = new int[fbLength];
                    boolean smallPalette = palette.length == 2;
                    for (int i = 0; i < inflatedSize; i++) {
                        int b = inflated[i];
                        if (b < 0) {
                            b += 256;
                        }
                        if (smallPalette) {
                            for (int j = 0; j < 8; j++) {
                                int pixel = 8 * i + j;
                                if (pixel >= fbLength) {
                                    break;
                                }

                                fbData[pixel] =
                                        palette[(b >> (7 - j)) & 0x1];
                            }
                        } else {
                            fbData[i] = palette[b];
                        }
                    }
                } else {
                    fbData = rgbBytesToInts(inflated);
                }

                store.store(new SliceStore.RawDataSlice(fbData, false, x, y, width, height, info));
            }
            break;
            case ENCODING_TIGHT_FILL: {
                byte[] rgbColor = read(3);
                int color = rgbBytesToInt(rgbColor, 0);
                System.out.println("tight fill: " + dump(rgbColor));

                int[] fbData = new int[width * height];
                for (int i = 0; i < fbData.length; i++) {
                    fbData[i] = color;
                }
                store.store(new SliceStore.JPEGSlice(createJPEG(width,
                        height, fbData), x, y, width, height, info));
            }
            break;
            case ENCODING_TIGHT_JPEG:
                int length = readTightLength();
                System.out.println("jpeg length: " + length);
                byte[] data = read(length);
                store.store(new SliceStore.JPEGSlice(data, x, y, width, height,
                        info));
                break;
            default:
                throw new IllegalArgumentException("tight mode: " + tightMode);
        }
    }

    private void applyGradient(byte[] data, int pixelStride, int rowStride,
            int rows) {
        int length = rowStride * rows;
        for (int i = 0; i < length; i += pixelStride) {
            int color = rgbBytesToInt(data, i);
            data[i] = applyGradient(data, pixelStride, rowStride, i, color, 16);
            data[i + 1] = applyGradient(data, pixelStride, rowStride, i + 1,
                    color, 8);
            data[i + 2] = applyGradient(data, pixelStride, rowStride, i + 2,
                    color, 0);
        }
    }

    private byte applyGradient(byte[] data, int pixelStride, int rowStride,
            int offset, int color, int shift) {
        boolean firstPixel = offset % rowStride < pixelStride;
        boolean firstLine = offset < rowStride;
        color = (color >> shift) & 0xff;
        int result = firstLine ? 0 : data[offset - rowStride];
        if (result < 0) {
            result += 256;
        }
        if (!firstPixel) {
            result -=
                    firstLine ? 0 : (data[offset - rowStride - pixelStride] < 0
                    ? (256 + data[offset - rowStride - pixelStride]) : data[offset - rowStride
                    - pixelStride]);
            result += data[offset - pixelStride] < 0 ? (256 + data[offset - pixelStride]) : data[offset
                    - pixelStride];
            result = Math.min(Math.max(0, result), 0xff);
        }
        result += color;
        result &= 0xff;
        return (byte) result;
    }

    private int readTightLength() throws IOException {
        int length = readInt(1);
        if (length >= 128) {
            length -= 128;
            length += readTightLength() * 128;
        }
        return length;
    }

    private byte swapBits(byte in) {
        byte out = 0;
        for (int i = 0; i < 8; i++) {
            byte bit = (byte) ((in >> i) & 0x1);
            out |= (bit << (7 - i));
        }
        return out;
    }

    private void swapBits(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = swapBits(data[i]);
        }
    }

    private int readInt(int bytes) throws IOException {
        long result = readLong(bytes);
        if (result > Integer.MAX_VALUE) {
            if (result >= 2l * Integer.MAX_VALUE + 2) {
                throw new IllegalArgumentException();
            }
            result -= 2l * Integer.MAX_VALUE + 2;
        }
        return (int) result;
    }

    private long readLong(int bytes) throws IOException {
        byte[] data = read(bytes);
        long result = 0;
        for (int i = 0; i < data.length; i++) {
            result *= 256;
            int b = data[i];
            if (b < 0) {
                b += 256;
            }
            result += b;
        }
        return result;
    }

    private byte[] read(long count) throws IOException {
        if (count > Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        int c = (int) count;
        byte[] result = new byte[c];
        int pos = 0;
        while (pos < count) {
            int read = in.read(result, pos, c - pos);
            if (read < 0) {
                throw new IllegalStateException();
            }
            pos += read;
        }
        return result;
    }

    private String write(byte[] data) throws IOException {
        socket.getOutputStream().write(data);
        return dump(data);
    }

    private String write(long value, int bytes) throws IOException {
        byte[] data = new byte[bytes];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) ((value >> (8 * (bytes - i - 1))) & 0xff);
        }
        return write(data);
    }

    private String dump(byte[] data) {
        return dump(data, 0, data.length);
    }

    private String dump(byte[] data, int offset, int length) {
        length = Math.min(data.length - offset, length);
        StringBuffer result = new StringBuffer();
        for (int i = offset; i < offset + length; i++) {
            int b = data[i];
            if (b < 0) {
                b = 256 + b;
            }
            result.append(Integer.toString(b, 16));
            result.append(" ");
        }
        return result.toString();
    }
}
