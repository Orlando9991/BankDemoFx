package pt.rumos.contract;
import java.util.List;
import java.util.Optional;


public interface IRepository<T,Tid> {
	public Optional<T> save(T obj);

	public Optional<T> saveObj(T obj);

	public Optional<T> updateObj(T obj);

	public Optional<T> findById(Tid id);

	public void deleteById(Tid id);

	public List<T> findAll();
}
