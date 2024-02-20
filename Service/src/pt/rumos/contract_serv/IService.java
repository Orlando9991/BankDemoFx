package pt.rumos.contract_serv;

import java.util.List;

public interface IService<T,Tid> {
	public T create(T obj);

	public T update(T obj);

	public T getById(Tid id);

	public void deleteById(Tid id);

	public List<T> getAll();
}
