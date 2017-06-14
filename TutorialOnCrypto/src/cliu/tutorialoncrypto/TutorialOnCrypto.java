package cliu.tutorialoncrypto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TutorialOnCrypto extends Activity {
    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        setContentView(R.layout.examples);
        
        Button balgs = (Button)findViewById(R.id.buttonalgs);
        balgs.setOnClickListener(new OnClickListener(){
			// @Override
			public void onClick(View arg0) {
				startActivity(new Intent(TutorialOnCrypto.this, ListCryptoAlgorithms.class));	
			}
		});
		
        Button bsymm = (Button)findViewById(R.id.buttonsymm);
        bsymm.setOnClickListener(new OnClickListener(){
			// @Override
			public void onClick(View arg0) {
				startActivity(new Intent(TutorialOnCrypto.this, SymmetricAlgorithmAES.class));	
			}
		});
        
        Button basym = (Button)findViewById(R.id.buttonasym);
        basym.setOnClickListener(new OnClickListener(){
			// @Override
			public void onClick(View arg0) {
				startActivity(new Intent(TutorialOnCrypto.this, AsymmetricAlgorithmRSA.class));	
			}
		});
        
    }
    
    @Override
    public void onResume() {
        super.onResume();
    }
    
    @Override
    public void onPause() {
        super.onPause();
    }
	
}
