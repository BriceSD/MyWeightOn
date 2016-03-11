package iust.android.tp1.saunierdebes.myweighton;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity
    extends Activity {

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  protected void onRestart() {
    super.onRestart();
  }

  @Override
  public void onStateNotSaved() {
    super.onStateNotSaved();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onPostResume() {
    super.onPostResume();
  }

  @Override
  public void onCreate(Bundle savedInstanceState,
      PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  public void closeContextMenu() {
    super.closeContextMenu();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final EditText weightInputField = (EditText) findViewById(R.id.weightField);

    final Switch lbsUnitSwitcher = (Switch) findViewById(R.id.switch1);

    final RadioButton moonChoice       = (RadioButton) findViewById(R.id.moonChoice);
    final RadioButton marsChoice       = (RadioButton) findViewById(R.id.marsChoice);
    final RadioButton mercuryChoice    = (RadioButton) findViewById(R.id.mercuryChoice);
    final RadioButton jupiterChoice    = (RadioButton) findViewById(R.id.jupiterChoice);

    Button computeButton    = (Button) findViewById(R.id.calculateButton);
    Button eraseDataButton = (Button) findViewById(R.id.eraseDatasButton);

    final TextView weightComputingResults =
        (TextView) findViewById(R.id.weightComputingResults);

    computeButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if(weightInputField.getText().toString().isEmpty())
          return;

        final String ERROR_UNKNOWN_PLANET_MESSAGE = "Unknown planet";
        String       result                       = getString(R.string.weightComputingResults) + " ";
        double       weight                       = Double.parseDouble(weightInputField.getText().toString());
        final double earthGravitationalConstant   = 9.8;
        final double moonGravitationalConstant    = 1.6;
        final double marsGravitationalConstant    = 3.7;
        final double mercuryGravitationalConstant = 3.6;
        final int    jupiterGravitationalConstant = 25;
        double       mass                         = weight / earthGravitationalConstant;


        //C’est moche, une classe se cache dans ces if/elseIf. Utiliser le polymorphisme serait mieux.
          if (moonChoice.isChecked())
            result += getString(R.string.theMoon) + " : " + String.format("%.02f",mass * moonGravitationalConstant);
          else if (marsChoice.isChecked())
            result += getString(R.string.planetMars) + " : " + String.format("%.02f",mass * marsGravitationalConstant);
          else if (mercuryChoice.isChecked())
            result += getString(R.string.planetMercury) + " : " + String.format("%.02f",mass * mercuryGravitationalConstant);
          else if (jupiterChoice.isChecked())
            result += getString(R.string.planetJupiter) + " : " + String.format("%.02f", mass * jupiterGravitationalConstant);
          else
            throw new IllegalArgumentException(ERROR_UNKNOWN_PLANET_MESSAGE);

          result += lbsUnitSwitcher.isChecked() ? " lbs." : " kg.";

          weightComputingResults.setText(result);
          weightComputingResults.setVisibility(View.VISIBLE);
      }
    });

    eraseDataButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        weightInputField.setText("");
        lbsUnitSwitcher.setChecked(false);
        moonChoice.setChecked(true);
        weightComputingResults.setVisibility(View.INVISIBLE);
      }
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
