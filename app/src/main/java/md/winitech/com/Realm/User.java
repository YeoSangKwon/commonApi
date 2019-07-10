/*
 * Create by SangKwon on 2019. 7. 8.
 */

package md.winitech.com.Realm;


import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class User extends RealmObject {

    /**
     *  PrimaryKey -> 기본키, 암묵적 @Index 포함
     *  Required -> non null
     *  Index -> 색인 기능
     */
    private String  name;
    private int     age;

    @Ignore
    private int     sessionId;

    public String   getName() { return name; }
    public void     setName(String name) { this.name = name; }
    public int      getAge() { return age; }
    public void     setAge(int age) { this.age = age; }
    public int      getSessionId() { return sessionId; }
    public void     setSessionId(int sessionId) { this.sessionId = sessionId; }

    public boolean hasLongName() {
        return name.length() > 7;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
