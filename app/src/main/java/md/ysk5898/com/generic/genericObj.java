/*
 * Create by SangKwon on 2019. 7. 26.
 */

package md.ysk5898.com.generic;

public class genericObj<T> {
    private T data;

    public genericObj(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
