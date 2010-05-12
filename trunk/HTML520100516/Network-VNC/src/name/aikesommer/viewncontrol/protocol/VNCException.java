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
public class VNCException extends Exception {

    public VNCException() {
    }

    public VNCException(Throwable cause) {
        super(cause);
    }

    public static class AuthenticationFailed extends VNCException {

    }

    public static class ConnectionLost extends VNCException {

        public ConnectionLost() {
        }

        public ConnectionLost(Throwable cause) {
            super(cause);
        }

    }

}
