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

/**
 *
 * @author Aike J Sommer
 */
public class PixelFormat {

    private int bits;
    private int depth;
    private boolean bigEndian;
    private boolean trueColor;
    private int redMax;
    private int greenMax;
    private int blueMax;
    private int redShift;
    private int greenShift;
    private int blueShift;

    public PixelFormat(byte[] data) {
        bits = value(data, 0, 1);
        depth = value(data, 1, 1);
        bigEndian = data[2] != 0;
        trueColor = data[3] != 0;
        redMax = value(data, 4, 2);
        greenMax = value(data, 6, 2);
        blueMax = value(data, 8, 2);
        redShift = value(data, 10, 1);
        greenShift = value(data, 11, 1);
        blueShift = value(data, 12, 1);
    }

    private int value(byte[] data, int offset, int bytes) {
        int result = 0;
        for (int i = offset; i < offset + bytes; i++) {
            result *= 256;
            int b = data[i];
            if (b < 0) {
                b += 256;
            }
            result += b;
        }
        return result;
    }

    /**
     * @return the bits
     */
    public int getBits() {
        return bits;
    }

    /**
     * @param bits the bits to set
     */
    public void setBits(int bits) {
        this.bits = bits;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * @return the bigEndian
     */
    public boolean isBigEndian() {
        return bigEndian;
    }

    /**
     * @param bigEndian the bigEndian to set
     */
    public void setBigEndian(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }

    /**
     * @return the trueColor
     */
    public boolean isTrueColor() {
        return trueColor;
    }

    /**
     * @param trueColor the trueColor to set
     */
    public void setTrueColor(boolean trueColor) {
        this.trueColor = trueColor;
    }

    /**
     * @return the redMax
     */
    public int getRedMax() {
        return redMax;
    }

    /**
     * @param redMax the redMax to set
     */
    public void setRedMax(int redMax) {
        this.redMax = redMax;
    }

    /**
     * @return the greenMax
     */
    public int getGreenMax() {
        return greenMax;
    }

    /**
     * @param greenMax the greenMax to set
     */
    public void setGreenMax(int greenMax) {
        this.greenMax = greenMax;
    }

    /**
     * @return the blueMax
     */
    public int getBlueMax() {
        return blueMax;
    }

    /**
     * @param blueMax the blueMax to set
     */
    public void setBlueMax(int blueMax) {
        this.blueMax = blueMax;
    }

    /**
     * @return the redShift
     */
    public int getRedShift() {
        return redShift;
    }

    /**
     * @param redShift the redShift to set
     */
    public void setRedShift(int redShift) {
        this.redShift = redShift;
    }

    /**
     * @return the greenShift
     */
    public int getGreenShift() {
        return greenShift;
    }

    /**
     * @param greenShift the greenShift to set
     */
    public void setGreenShift(int greenShift) {
        this.greenShift = greenShift;
    }

    /**
     * @return the blueShift
     */
    public int getBlueShift() {
        return blueShift;
    }

    /**
     * @param blueShift the blueShift to set
     */
    public void setBlueShift(int blueShift) {
        this.blueShift = blueShift;
    }
    
}
