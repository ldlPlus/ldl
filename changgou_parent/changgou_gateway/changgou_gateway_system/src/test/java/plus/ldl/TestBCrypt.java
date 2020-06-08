package plus.ldl;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author ldl.plus
 * @date 2020年06月05日  21:59
 * 密码加密
 */
public class TestBCrypt {
    public static void main(String[] args) {
        // 获取盐
        String gensalt = BCrypt.gensalt();
        System.out.println("gensalt = " + gensalt);
        for (int i = 0; i < 10; i++) {
            // 基于当前的盐进行密码加密
            String hashpw = BCrypt.hashpw("123456", gensalt);
            System.out.println("hashpw = " + hashpw);

            // 解密
            boolean checkpw = BCrypt.checkpw("123456", hashpw);
            System.out.println("checkpw = " + checkpw);
        }
    }
}
