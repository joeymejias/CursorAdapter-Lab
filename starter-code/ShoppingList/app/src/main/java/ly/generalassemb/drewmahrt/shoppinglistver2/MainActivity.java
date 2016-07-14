package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.shopping_list_view);

        //Ignore the two lines below
        DBAssetHelper dbAssetHelper = new DBAssetHelper(MainActivity.this);
        dbAssetHelper.getReadableDatabase();

        Cursor cursor = MySQLiteOpenHelper
                .getInstance(this).getIconList();

        android.support.v4.widget.CursorAdapter adapter = new android.support.v4.widget.CursorAdapter(MainActivity.this, cursor, android.support.v4.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.grocery_list_item, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView itemName = (TextView) view.findViewById(R.id.textview_grocery_item_name);

                int colIndex = cursor.getColumnIndex(MySQLiteOpenHelper.COL_ITEM_NAME);
//                int colIndex = cursor.getColumnIndex(MySQLiteOpenHelper.COL_DESCRIPTION); //Hypothetical Description search
                String name = cursor.getString(colIndex);

                itemName.setText(name);
            }
        };

        mListView.setAdapter(adapter);

    }
}
