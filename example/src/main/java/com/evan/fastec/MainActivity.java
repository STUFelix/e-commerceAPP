package com.evan.fastec;





import com.evan.latte.activities.ProxyActivity;
import com.evan.latte.delegates.LatteDelegate;
import com.evan.latte.net.RestClient;

public class MainActivity extends ProxyActivity {


    @Override
    public LatteDelegate setLatteDelegate() {
        return new ExampleDelegate();

    }

}
