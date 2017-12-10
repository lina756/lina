package angel.model.vto;

/**
 * Created by 磷啊 on 2017/12/10.
 */
public class CheckVto {
    private String ids;
    private String checkPersonName;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCheckPersonName() {
        return checkPersonName;
    }

    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
    }

    @Override
    public String toString() {
        return "CheckVto{" +
                ", ids='" + ids + '\'' +
                ", checkPersonName='" + checkPersonName + '\'' +
                '}';
    }
}
