package plus.ldl.service.impl;


import org.springframework.stereotype.Service;
import plus.ldl.service.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService {
    @Override
    public void show1() {
        String str = null;
        int length = str.length(); // 空指针异常
    }

    @Override
    public void show2() {
        String str = "abc";
        int num = Integer.parseInt(str); // 类型转换异常
    }

    @Override
    public void show3() {
        int i = 1 / 0;  // 除0异常
    }
}
