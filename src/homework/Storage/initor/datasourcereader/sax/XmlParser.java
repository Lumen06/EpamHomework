package homework.Storage.initor.datasourcereader.sax;

public interface XmlParser<T> {
    T parse(String file) throws Exception;
}
