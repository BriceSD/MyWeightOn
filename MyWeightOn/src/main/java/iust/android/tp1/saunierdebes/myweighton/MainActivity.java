package iust.android.tp1.saunierdebes.myweighton;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity
    extends Activity {

// -------------------------- OTHER METHODS --------------------------

  @Override
  public void closeContextMenu() {
    super.closeContextMenu();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button computeButton   = (Button) findViewById(R.id.calculateButton);
    Button eraseDataButton = (Button) findViewById(R.id.eraseDatasButton);
    Switch unitSwitcher    = (Switch) findViewById(R.id.unitSwitcher);

    unitSwitcher.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        updateWeightResultMessage();
      }
    });

    computeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        updateWeightResultMessage();
      }
    });

    eraseDataButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        resetInputFields();
      }
    });

  }

  private void updateWeightResultMessage() {
    final EditText weightInputField = (EditText) findViewById(R.id.weightField);

    if (weightInputField.getText().toString().isEmpty())
      return;

    final TextView weightComputingResults = (TextView) findViewById(R.id.weightComputedResults);
    weightComputingResults.setText(computedWeightMessage());
    weightComputingResults.setVisibility(View.VISIBLE);
  }

  // TODO: 11/03/16 Refactor in an interface and 5 classes. An abstract factory would be better too.
  private String computedWeightMessage() {
    final Switch   lbsUnitSwitcher  = (Switch) findViewById(R.id.unitSwitcher);
    final EditText weightInputField = (EditText) findViewById(R.id.weightField);

    final RadioButton moonChoice    = (RadioButton) findViewById(R.id.moonChoice);
    final RadioButton marsChoice    = (RadioButton) findViewById(R.id.marsChoice);
    final RadioButton mercuryChoice = (RadioButton) findViewById(R.id.mercuryChoice);
    final RadioButton jupiterChoice = (RadioButton) findViewById(R.id.jupiterChoice);

    final String ERROR_UNKNOWN_PLANET_MESSAGE = "Unknown planet";
    String       result                       = getString(R.string.weightComputingResults) + " ";
    double weight = Double.parseDouble(weightInputField.getText().toString());
    final double earthGravitationalConstant   = 9.8;
    final double moonGravitationalConstant    = 1.6;
    final double marsGravitationalConstant    = 3.7;
    final double mercuryGravitationalConstant = 3.6;
    final int    jupiterGravitationalConstant = 25;
    double       mass                         = weight / earthGravitationalConstant;

    if (moonChoice.isChecked())
      result += getString(R.string.theMoon) + " : " + String
          .format("%.02f", mass * moonGravitationalConstant);
    else if (marsChoice.isChecked())
      result += getString(R.string.planetMars) + " : " + String
          .format("%.02f", mass * marsGravitationalConstant);
    else if (mercuryChoice.isChecked())
      result += getString(R.string.planetMercury) + " : " + String
          .format("%.02f", mass * mercuryGravitationalConstant);
    else if (jupiterChoice.isChecked())
      result += getString(R.string.planetJupiter) + " : " + String
          .format("%.02f", mass * jupiterGravitationalConstant);
    else
      throw new IllegalArgumentException(ERROR_UNKNOWN_PLANET_MESSAGE);

    result += lbsUnitSwitcher.isChecked() ? " lbs." : " kg.";
    return result;
  }

  private void resetInputFields() {
    final EditText    weightInputField       = (EditText) findViewById(R.id.weightField);
    final Switch      lbsUnitSwitcher        = (Switch) findViewById(R.id.unitSwitcher);
    final RadioButton moonChoice             = (RadioButton) findViewById(R.id.moonChoice);
    final TextView    weightComputingResults = (TextView) findViewById(R.id.weightComputedResults);

    weightInputField.setText("");
    lbsUnitSwitcher.setChecked(false);
    moonChoice.setChecked(true);
    weightComputingResults.setVisibility(View.INVISIBLE);
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

  protected void onResume() {
    super.onResume();

    TextView weightComputedResults = (TextView) findViewById(R.id.weightComputedResults);
    if (!weightComputedResults.getText().toString().isEmpty())
      updateWeightResultMessage();
  }
}
