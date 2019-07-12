package joseph.youcef.shopili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class ProductDetails extends AppCompatActivity {

    private static final String STATE_PRICE = "price";
    private String savedPrice;

    ImageView imageView;
    TextView priceVal;
    TextView description;
    TextView delivery;
    TextView guarantee;
    TextView link;
    Product product;
    Button button1;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");

        button1 = findViewById(R.id.b1);
        button2 = findViewById(R.id.b2);

        imageView = findViewById(R.id.image);
        imageView.setImageResource(product.getPhoto());
        priceVal = findViewById(R.id.priceval);
        description = findViewById(R.id.description);
        description.setText(product.getDescription());
        delivery = findViewById(R.id.delivery);
        delivery.setText(product.getDelivery());
        guarantee = findViewById(R.id.guarantee);
        guarantee.setText(product.getGuarantee());
        link = findViewById(R.id.link);
        link.setText(product.getLink());

        // If we have a saved state then we can restore it now
        if (savedInstanceState != null) {
            savedPrice = savedInstanceState.getString(STATE_PRICE,"");
            priceVal.setText(savedPrice);
            priceVal.setVisibility(View.VISIBLE);

            button1.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
        }


    }

    public void priceG(View view) {
        priceVal.setText(this.product.getPrice_g()+" DA");
        priceVal.setVisibility(View.VISIBLE);

        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
    }

    public void priceWG(View view) {
        priceVal.setText(this.product.getPrice_wg()+" DA");
        priceVal.setVisibility(View.VISIBLE);

        button1.setVisibility(View.GONE);
        button2.setVisibility(View.GONE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_PRICE,priceVal.getText().toString());
    }
}
