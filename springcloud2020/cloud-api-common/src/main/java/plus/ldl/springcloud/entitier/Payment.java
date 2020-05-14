package plus.ldl.springcloud.entitier;

import java.io.Serializable;

/**
 * @author ldl.plus
 */
public class Payment implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 支付流水号
     */
    private String serial;

    private static final long serialVersionUID = 1L;

    public Payment(Long id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}

