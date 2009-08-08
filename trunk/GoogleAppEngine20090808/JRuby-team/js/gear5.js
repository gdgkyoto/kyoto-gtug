// ======== from gears_init.js ===========
// Copyright 2007, Google Inc.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
//  1. Redistributions of source code must retain the above copyright notice,
//     this list of conditions and the following disclaimer.
//  2. Redistributions in binary form must reproduce the above copyright notice,
//     this list of conditions and the following disclaimer in the documentation
//     and/or other materials provided with the distribution.
//  3. Neither the name of Google Inc. nor the names of its contributors may be
//     used to endorse or promote products derived from this software without
//     specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
// EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
// OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
// OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
// ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//
// Sets up google.gears.*, which is *the only* supported way to access Gears.
//
// Circumvent this file at your own risk!
//
// In the future, Gears may automatically define google.gears.* without this
// file. Gears may use these objects to transparently fix bugs and compatibility
// issues. Applications that use the code below will continue to work seamlessly
// when that happens.

(function(){
    // We are already defined. Hooray!
    if (window.google && google.gears) {
        return;
    }
    
    var factory = null;
    
    // Firefox
    if (typeof GearsFactory != 'undefined') {
        factory = new GearsFactory();
    }
    else {
        // IE
        try {
            factory = new ActiveXObject('Gears.Factory');
            // privateSetGlobalObject is only required and supported on WinCE.
            if (factory.getBuildInfo().indexOf('ie_mobile') != -1) {
                factory.privateSetGlobalObject(this);
            }
        } 
        catch (e) {
            // Safari
            if ((typeof navigator.mimeTypes != 'undefined') &&
            navigator.mimeTypes["application/x-googlegears"]) {
                factory = document.createElement("object");
                factory.style.display = "none";
                factory.width = 0;
                factory.height = 0;
                factory.type = "application/x-googlegears";
                document.documentElement.appendChild(factory);
            }
        }
    }
    
    // *Do not* define any objects if Gears is not installed. This mimics the
    // behavior of Gears defining the objects in the future.
    if (!factory) {
        return;
    }
    
    // Now set up the objects, being careful not to overwrite anything.
    //
    // Note: In Internet Explorer for Windows Mobile, you can't add properties to
    // the window object. However, global objects are automatically added as
    // properties of the window object in all browsers.
    if (!window.google) {
        google = {};
    }
    
    if (!google.gears) {
        google.gears = {
            factory: factory
        };
    }
})();
// ======== end gears_init.js ===========

(function initConsole(_global){
    // this code is copied from dojo-release-1.1.1. thanks!
    if (!_global["console"]) {
        _global.console = {
            log: function(message){
				var con = document.getElementById("console");
				if (!con) {
					con.innerHTML += "<div>" + message + "</div>";
				}
            } // no-op
        };
    }
    
    var cn = ["assert", "count", "debug", "dir", "dirxml", "error", "group", "groupEnd", "info", "profile", "profileEnd", "time", "timeEnd", "trace", "warn", "log"];
    var i = 0, tn;
    while ((tn = cn[i++])) {
        if (!console[tn]) {
            (function(){
                var tcn = tn + "";
                console[tcn] = function(){
                    var a = Array.apply({}, arguments);
                    a.unshift(tcn + ":");
                    console.log(a.join(" "));
                }
            })();
        }
    }
})(this);

(function(_global){
    _global.gear5 = {};
    
    var databases = {};
    function hasGears(){
        return !!(_global["google"] &&
        _global.google["gears"]);
		// &&
        //typeof _global.google.gears.factory.create == "function");
    }
    if (!hasGears()) {
        console.log("gears is missing.");
        return;
    }
    function select(db, sql, args){
        var rs = db.execute(sql, args);
        try {
            var results = [];
            while (rs.isValidRow()) {
                var row = {};
                for (var i = 0, n = rs.fieldCount(); i < n; i++) {
                    row[rs.fieldName(i)] = rs.field(i);
                }
                results.push(row);
                rs.next();
            }
            return results;
        }
        finally {
            if (rs) 
                rs.close();
        }
    }
    function selectOne(db, sql, args){
        var results = select(db, sql, args);
        if (results.length == 0) {
            return null;
        }
        return results[0];
    }
    function openGearsDatabase(name){
        if (databases[name]) {
            return databases[name].db;
        }
        var db = _global.google.gears.factory.create("beta.database");
        db.open(name);
        // transactionDepth is a counter for nested transaction.
        // I want to define this property on db direct, but IE don't allow
        // to modify the GearsDatabase object.
        // So, I define transactionDepth property and db here.
        databases[name] = {
            db: db,
            transactionDepth: 0
        };
        return db;
    }
    var metadataTableName = "gear5_db_metadata";
    function createMetadataTable(dbName){
        var db = openGearsDatabase(dbName);
        db.execute("create table if not exists " + metadataTableName + "(version, displayName, estimatedSize integer)");
    }
    function makeSQLError(code, message){
        var error = new Error();
        error.code = code;
        error.message = message;
        return error;
    }
    gear5.database = {
        reset: function(dbName){
            var db = openGearsDatabase(dbName);
            db.execute("drop table if exists " + metadataTableName);
            createMetadataTable(dbName);
        }
    };
    function installWebDatabase(){
        if (_global["openDatabase"] !== undefined) {
            console.log("openDatabase is defined, do nothing");
            return;
        }
        var validateCheckVersion = function(db, oldVersion, newVersion){
            if (typeof oldVersion != "string" || typeof newVersion != "string") {
                throw new Error("version must be string");
            }
            var meta = selectOne(db, "select * from " + metadataTableName);
            if (meta.version !== oldVersion) {
                throw new Error("invalid version is specified:" + oldVersion);
            }
        }
        var Gear5Database = function(name, expectedVersion, displayName, estimatedSize){
            if (typeof name != "string") {
                throw new Error("database name must be string, actual:" + name);
            }
            if (typeof expectedVersion != "string") {
                throw new Error("database name must be string, actual:" + name);
            }
            if (displayName === undefined) {
                displayName = null;
            }
            if (displayName != null && typeof displayName != "string") {
                displayName = String(displayName);
            }
            if (estimatedSize === undefined) {
                estimatedSize = null;
            }
            if (estimatedSize !== null) {
                if (typeof estimatedSize == "string") {
                    estimatedSize = parseInt(estimatedSize, 10);
                }
                if (isNaN(estimatedSize) || typeof estimatedSize != "number" || estimatedSize < 0) {
                    throw new Error("estimated size must be valid integer");
                }
            }
            this._db = openGearsDatabase(name);
            createMetadataTable(name);
            var meta = selectOne(this._db, "select * from " + metadataTableName);
            if (!meta) {
                this._db.execute("insert into " + metadataTableName + "(version, displayName, estimatedSize) " +
                "values (?, ?, ?)", [expectedVersion, displayName, estimatedSize]);
                this.version = expectedVersion;
            }
            else {
                if (expectedVersion !== "" && meta.version != expectedVersion) {
                    throw new Error("INVALID_STATE_ERROR");
                }
                this.version = meta.version;
            }
            this._name = name;
            this._displayName = displayName;
            this._estimatedSize = estimatedSize;
        };
        Gear5Database.prototype = {
            transaction: function(callback, errorCallback, successCallback){
            
                if (typeof callback != "function") {
                    throw new Error("callback is required, and must be function");
                }
                if (errorCallback != null && errorCallback != undefined && typeof errorCallback != "function") {
                    throw new Error("errorCallback must be function");
                }
                if (successCallback != null && successCallback != undefined && typeof successCallback != "function") {
                    throw new Error("successCallback must be function");
                }
                var tx = new Gear5Transaction(this);
                tx._runAsync(callback, errorCallback, successCallback);
            },
            readTransaction: function(callback, errorCallback, successCallback){
                this.transaction(callback, errorCallback, successCallback);
            },
            changeVersion: function(oldVersion, newVersion, callback, errorCallback, successCallback){
                validateCheckVersion(this._db, oldVersion, newVersion);
                if (typeof callback != "function") {
                    throw new Error("callback is required, and must be function");
                }
                if (errorCallback !== null && errorCallback !== undefined && typeof errorCallback != "function") {
                    throw new Error("errorCallback must be function");
                }
                if (successCallback !== null && successCallback !== null && typeof successCallback != "function") {
                    throw new Error("successCallback must be function");
                }
                var tx = new Gear5Transaction(this);
                var self = this;
                tx._runAsync(function(){
                    callback(tx);
                    self._db.execute("update " + metadataTableName + " set version = ?", [newVersion]);
                    self.version = newVersion;
                }, errorCallback, successCallback);
            }
        };
        var Gear5Transaction = function(gear5Db){
            this._cachedDb = databases[gear5Db._name];
        };
        Gear5Transaction.prototype = {
            _begin: function(){
                if (this._cachedDb.transactionDepth <= 0) 
                    this._cachedDb.db.execute("BEGIN");
                this._cachedDb.transactionDepth++;
            },
            _commit: function(){
                this._cachedDb.transactionDepth = Math.max(0, --this._cachedDb.transactionDepth);
                if (this._cachedDb.transactionDepth <= 0) 
                    this._cachedDb.db.execute("COMMIT");
            },
            _rollback: function(){
                if (this._cachedDb.transactionDepth > 0) {
                    this._cachedDb.transactionDepth = 0;
                    this._cachedDb.db.execute("ROLLBACK");
                }
            },
            _run: function(callback, errorCallback, successCallback){
                var db = this._cachedDb.db;
                try {
                    this._begin();
                    var ret = callback(this);
                    this._commit();
                    if (successCallback) 
                        successCallback();
                    return ret;
                } 
                catch (e) {
                    this._rollback();
                    if (errorCallback) {
                        errorCallback(makeSQLError(1, e.message));
                    }
                    else {
                        console.error(e);
                    }
                }
            },
            _runAsync: function(callback, errorCallback, successCallback){
                var self = this;
                setTimeout(function(){
                    self._run.call(self, callback, errorCallback, successCallback);
                }, 0);
            },
            executeSql: function(sql, args, callback, errorCallback){
                var self = this;
                if (!sql) {
                    throw new Error("invalid sql:\"" + sql + "\"");
                }
                if (args && !(args instanceof Array)) {
                    throw new Error("sql parameter must be Array");
                }
                if (callback !== null && callback !== undefined && typeof callback != "function") {
                    throw new Error("callback must be function");
                }
                if (errorCallback !== null && errorCallback !== undefined && typeof errorCallback != "function") {
                    throw new Error("errorCallback must be function");
                }
                
                //setTimeout(function(){
                try {
                    var rs = new Gear5ResultSet(sql, args, self._cachedDb.db);
                    if (callback) {
                        callback(self, rs);
                    }
                } 
                catch (e) {
                    if (errorCallback) {
                        errorCallback(self, makeSQLError(1, e.message));
                    }
                    else {
                        console.error(e);
                    }
                }
                //}, 0)
            }
        };
        var Gear5DatabaseSync = function(name, version, displayName, estimatedSize){
            Gear5Database.apply(this, [name, version, displayName, estimatedSize]);
        };
        Gear5DatabaseSync.prototype = {
            transaction: function(){
                return new Gear5TransactionSync(this);
            },
            readTransaction: function(){
                return new Gear5TransactionSync(this);
            },
            changeVersion: function(oldVersion, newVersion){
                validateCheckVersion(this._db, oldVersion, newVersion);
                return new Gear5TransactionSync(this, newVersion);
            }
        };
        var Gear5TransactionSync = function(gear5Db, newDBVersion){
            this._gear5Db = gear5Db;
            this._cachedDb = databases[gear5Db._name];
            this._newDBVersion = newDBVersion;
            this._begin();
        };
        Gear5TransactionSync.prototype = {
            _begin: function(){
                if (this._cachedDb.transactionDepth <= 0) 
                    this._cachedDb.db.execute("BEGIN");
                this._cachedDb.transactionDepth++;
            },
            commit: function(){
                this._cachedDb.transactionDepth = Math.max(0, --this._cachedDb.transactionDepth);
                if (this._cachedDb.transactionDepth <= 0) {
                    if (this._newDBVersion !== undefined) {
                        this._cachedDb.db.execute("update " + metadataTableName + " set version = ?", [this._newDBVersion]);
                        this._gear5Db.version = this._newDBVersion;
                    }
                    this._cachedDb.db.execute("COMMIT");
                }
            },
            rollback: function(){
                if (this._cachedDb.transactionDepth > 0) {
                    this._cachedDb.transactionDepth = 0;
                    this._cachedDb.db.execute("ROLLBACK");
                }
            },
            executeSql: function(sql, args){
                var self = this;
                if (!sql) {
                    throw new Error("invalid sql:\"" + sql + "\"");
                }
                if (args && !(args instanceof Array)) {
                    throw new Error("sql parameter must be Array");
                }
                return new Gear5ResultSet(sql, args, self._cachedDb.db);
            }
        };
        var Gear5ResultSet = function(sql, args, db){
            var rs = db.execute(sql, args);
            this.rows = [];
            this.rows.item = function(index){
                return this[index];
            };
            try {
                while (rs.isValidRow()) {
                    var row = {};
                    for (var i = 0, n = rs.fieldCount(); i < n; i++) {
                        row[rs.fieldName(i)] = rs.field(i);
                    }
                    this.rows.push(row);
                    rs.next();
                }
                this.insertId = db.lastInsertRowId;
                if (sql.match(/^\s*insert/) || sql.match(/^\s*update/) || sql.match(/^\s*delete/)) {
                    this.rowsAffected = db.rowsAffected;
                }
                else {
                    this.rowsAffected = 0;
                }
            }
            finally {
                rs.close();
            }
        };
        _global["openDatabase"] = function(dbName, version, displayName, estimatedSize){
            return new Gear5Database(dbName, version, displayName, estimatedSize);
        };
        function isWorkerThread(){
            return !_global["window"];
        }
        //if (isWorkerThread()) {}
        _global["openDatabaseSync"] = function(dbName, version, displayName, estimatedSize){
            return new Gear5DatabaseSync(dbName, version, displayName, estimatedSize);
        };
    }
    var GEAR5_STORAGE_DATABASE = "gear5_storage";
    var GEAR5_LOCAL_STORAGE_TABLE = "gear5_local_storage";
    var Gear5StorageEvent = function(){
    };
    Gear5StorageEvent.prototype = {
        initStorageEvent: function(type, canBubble, cancelable, key, oldValue, newValue, url, source, storageArea){
            this.key = key;
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.url = url;
            this.source = source;
            this.storageArea = storageArea;
        },
        initStorageEventNS: function(namespaceURI, type, canBubble, cancelable, key, oldValue, newValue, url, source, storageArea){
            this.initStorageEvent(type, canBubble, cancelable, key, oldValue, newValue, url, source, storageArea);
        }
    };
    var Gear5LocalStorage = function(){
        var db = this._db = openGearsDatabase(GEAR5_STORAGE_DATABASE);
        db.execute("create table if not exists " + GEAR5_LOCAL_STORAGE_TABLE + "(name text primary key, value text)");
        this._refreshLength();
    };
    Gear5LocalStorage.prototype = {
        _refreshLength: function(){
            this.length = this.gear5_getLength();
        },
        gear5_getLength: function(){
            var count = selectOne(this._db, "select count(*) as count from " + GEAR5_LOCAL_STORAGE_TABLE);
            return count.count;
        },
        key: function(index){
            if (index < 0) {
                throw new Error("index must be positive integer:" + index);
            }
            if (typeof index != "number") {
                var idx = parseInt(index);
                if (isNaN(idx)) {
                    throw new Error("index must be positive integer:" + index);
                }
                index = idx;
            }
            var key = selectOne(this._db, "select name from " + GEAR5_LOCAL_STORAGE_TABLE + " order by name limit 1 offset ?", [index]);
            return key ? key.name : null;
        },
        getItem: function(key){
            var value = selectOne(this._db, "select value from " + GEAR5_LOCAL_STORAGE_TABLE + " where name=?", [key]);
            return value ? value.value : null;
        },
        setItem: function(key, data){
            if (key === null || key === undefined) {
                throw new Error("key must not be null or undefined");
            }
            key = String(key);
            data = String(data);
            this._db.execute("replace into " + GEAR5_LOCAL_STORAGE_TABLE + "(name, value) values (?, ?)", [key, data]);
            this._refreshLength();
        },
        removeItem: function(key){
            this._db.execute("delete from " + GEAR5_LOCAL_STORAGE_TABLE + " where name=?", [key]);
            this._refreshLength();
        },
        clear: function(){
            this._db.execute("delete from " + GEAR5_LOCAL_STORAGE_TABLE);
            this.length = 0;
        }
    };
    
    function installWebStorage(){
        if (_global["localStorage"]) {
            _global.localStorage.gear5_getLength = function(){
                return this.length;
            };
            return;
        }
        _global["localStorage"] = new Gear5LocalStorage();
    }
    function installGeolocation(){
        if (navigator.geolocation) {
            return;
        }
        navigator.geolocation = google.gears.factory.create("beta.geolocation");
    }
    installWebDatabase();
    installWebStorage();
    installGeolocation();
    
})(this);


