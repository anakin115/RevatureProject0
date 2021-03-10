package jdbc;

import model.User;
import util.MyCollection;
import util.MyList;

/**
 *
 * @param <T> the class used for this dao object.
 * @param <I> the primary key used by this class.
 */

public interface GenericDao<T,I> {


    int save(T t);

    boolean registerToBe(T u,int x);

    int checkAccountType(I s);

    T getByString(I s);

    T getByInt(int id);

    MyList<T> getAll();

    boolean remove(I id);

    MyList<T> getOwn(I s);

    String getPayment(I s);

    int rejectAllOtherOffer(int id);

    int accept(int s);

    int reject(int s);

    int chageCarFields(int s);

    boolean hasAOwner(I id);

    String getAllSoldCars();
}
