package hiy.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-03-07 11:26:58")
/** */
public final class CardMeta extends org.slim3.datastore.ModelMeta<hiy.model.Card> {

    /** */
    public final org.slim3.datastore.UnindexedAttributeMeta<hiy.model.Card, com.google.appengine.api.datastore.Blob> image = new org.slim3.datastore.UnindexedAttributeMeta<hiy.model.Card, com.google.appengine.api.datastore.Blob>(this, "image", "image", com.google.appengine.api.datastore.Blob.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hiy.model.Card, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<hiy.model.Card, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hiy.model.Card, java.lang.Integer> power = new org.slim3.datastore.CoreAttributeMeta<hiy.model.Card, java.lang.Integer>(this, "power", "power", int.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hiy.model.Card> user = new org.slim3.datastore.StringAttributeMeta<hiy.model.Card>(this, "user", "user");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hiy.model.Card, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<hiy.model.Card, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final CardMeta slim3_singleton = new CardMeta();

    /**
     * @return the singleton
     */
    public static CardMeta get() {
       return slim3_singleton;
    }

    /** */
    public CardMeta() {
        super("Card", hiy.model.Card.class);
    }

    @Override
    public hiy.model.Card entityToModel(com.google.appengine.api.datastore.Entity entity) {
        hiy.model.Card model = new hiy.model.Card();
        model.setImage((com.google.appengine.api.datastore.Blob) entity.getProperty("image"));
        model.setKey(entity.getKey());
        model.setPower(longToPrimitiveInt((java.lang.Long) entity.getProperty("power")));
        model.setUser((java.lang.String) entity.getProperty("user"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        hiy.model.Card m = (hiy.model.Card) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("image", m.getImage());
        entity.setProperty("power", m.getPower());
        entity.setProperty("user", m.getUser());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        hiy.model.Card m = (hiy.model.Card) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        hiy.model.Card m = (hiy.model.Card) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        hiy.model.Card m = (hiy.model.Card) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        hiy.model.Card m = (hiy.model.Card) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        hiy.model.Card m = (hiy.model.Card) model;
        writer.beginObject();
        org.slim3.datastore.json.JsonCoder encoder = null;
        if(m.getImage() != null && m.getImage().getBytes() != null){
            writer.setNextPropertyName("image");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getImage());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getKey());
        }
        writer.setNextPropertyName("power");
        encoder = new org.slim3.datastore.json.Default();
        encoder.encode(writer, m.getPower());
        if(m.getUser() != null){
            writer.setNextPropertyName("user");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getUser());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder = new org.slim3.datastore.json.Default();
            encoder.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    public hiy.model.Card jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        hiy.model.Card m = new hiy.model.Card();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.JsonCoder decoder = null;
        reader = rootReader.newObjectReader("image");
        decoder = new org.slim3.datastore.json.Default();
        m.setImage(decoder.decode(reader, m.getImage()));
        reader = rootReader.newObjectReader("key");
        decoder = new org.slim3.datastore.json.Default();
        m.setKey(decoder.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("power");
        decoder = new org.slim3.datastore.json.Default();
        m.setPower(decoder.decode(reader, m.getPower()));
        reader = rootReader.newObjectReader("user");
        decoder = new org.slim3.datastore.json.Default();
        m.setUser(decoder.decode(reader, m.getUser()));
        reader = rootReader.newObjectReader("version");
        decoder = new org.slim3.datastore.json.Default();
        m.setVersion(decoder.decode(reader, m.getVersion()));
    return m;
}
}