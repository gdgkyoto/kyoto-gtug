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

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Aike J Sommer
 */
public abstract class SliceStore {

    public static enum DataType {
        JPEG("image/jpeg"), PNG("image/png"), None(null);

        private String mimetype;

        private DataType(String mimetype) {
            this.mimetype = mimetype;
        }

        public String mimetype() {
            return mimetype;
        }

    }

    public static enum SliceType {
        Regular, Cursor
    }

    public void store(Slice slice) {
        store(SliceType.Regular, slice);
    }

    public abstract void store(SliceType type, Slice slice);
    public abstract void clear();

    public abstract static class Slice {

//        public byte[] data;
        public int x;
        public int y;
        public int width;
        public int height;
        public Map<String, Object> info;

        public Slice(int x, int y, int width, int height, Map<String, Object> info) {
//            this.data = data;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.info = info;
        }

        public abstract DataType type();
        public abstract byte[] data();
        public abstract boolean hasData();

    }

    public static class JPEGSlice extends Slice {

        private byte[] data;

        public JPEGSlice(byte[] data, int x, int y, int width, int height, Map<String, Object> info) {
            super(x, y, width, height, info);
            this.data = data;
        }

        @Override
        public DataType type() {
            return DataType.JPEG;
        }

        @Override
        public byte[] data() {
            return data;
        }

        @Override
        public boolean hasData() {
            return data != null;
        }

    }

    public static class PNGSlice extends Slice {

        private byte[] data;

        public PNGSlice(byte[] data, int x, int y, int width, int height, Map<String, Object> info) {
            super(x, y, width, height, info);
            this.data = data;
        }

        @Override
        public DataType type() {
            return DataType.PNG;
        }

        @Override
        public byte[] data() {
            return data;
        }

        @Override
        public boolean hasData() {
            return data != null;
        }

    }

    public static class NoDataSlice extends Slice {

        public NoDataSlice(int x, int y, int width, int height, Map<String, Object> info) {
            super(x, y, width, height, info);
        }

        @Override
        public DataType type() {
            return DataType.None;
        }

        @Override
        public byte[] data() {
            return null;
        }

        @Override
        public boolean hasData() {
            return false;
        }

    }

    public static class RawDataSlice extends Slice {

        private int[] rawData;
        private byte[] data = null;
        private boolean alpha;

        public RawDataSlice(int[] rawData, boolean alpha, int x, int y, int width, int height, Map<String, Object> info) {
            super(x, y, width, height, info);
            this.rawData = rawData;
            this.alpha = alpha;
        }

        @Override
        public DataType type() {
            return alpha ? DataType.PNG : DataType.JPEG;
        }

        @Override
        public byte[] data() {
            if (data != null || rawData == null) {
                return data;
            }

            data = alpha ? createPNG() : createJPEG();
            rawData = null;
            return data;
        }

        private byte[] createJPEG() {
            try {
                return VNCSession.createJPEG(width, height, rawData);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        private byte[] createPNG() {
            try {
                return VNCSession.createPNG(width, height, rawData);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public boolean hasData() {
            return data != null || rawData != null;
        }

    }

}
