package Model;

public interface DaoCreateUpdate<T> {

    void save(T t) throws Throwable;

    void update(T t) throws Throwable;
}
