package sidiq.com.estore;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;

public class DetailTransaksiPelangganActivity extends AppCompatActivity {

    public static final String EXTRA_ID_TRANSAKSI_PELANGGAN = "extraIdTransaksiPelanggan";
    public static final String EXTRA_NAMA_PELANGGAN_TRANSAKSI_PELANGGAN = "extraNamaPelangganTransaksiPelanggan";
    public static final String EXTRA_LIST_TRANSAKSI_PELANGGAN = "extraListTransaksiPelanggan";
    public static final String EXTRA_TOTAL_TRANSAKSI_PELANGGAN = "extraTotalTransaksiPelanggan";
    public static final String EXTRA_TANGGAL_TRANSAKSI_PELANGGAN = "extraTanggalTransaksiPelanggan";

    @BindView(R.id.txt_id_detailTrans_pelanggan)
    TextView txtIdDetailTransPelanggan;
    @BindView(R.id.txt_nama_detailTrans_pelanggan)
    TextView txtNamaDetailTransPelanggan;
    @BindView(R.id.txt_list_detailTrans_pelanggan)
    TextView txtListDetailTransPelanggan;
    @BindView(R.id.txt_totalHarga_detailTrans_pelanggan)
    TextView txtTotalHargaDetailTransPelanggan;
    @BindView(R.id.txt_tanggal_detailTrans_pelanggan)
    TextView txtTanggalDetailTransPelanggan;
    @BindView(R.id.btn_pdf_pelanggan)
    Button btnPdfPelanggan;
    @BindView(R.id.pdfPelanggan)
    LinearLayout pdfPelanggan;

    private Bitmap bitmap;
    Context context;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi_pelanggan);
        ButterKnife.bind(this);

        context = this;
        preferencedConfig = new SharedPreferencedConfig(this);

        ActivityCompat.requestPermissions(DetailTransaksiPelangganActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        txtIdDetailTransPelanggan.setText(getIntent().getStringExtra(EXTRA_ID_TRANSAKSI_PELANGGAN));
        txtNamaDetailTransPelanggan.setText(getIntent().getStringExtra(EXTRA_NAMA_PELANGGAN_TRANSAKSI_PELANGGAN));
        txtListDetailTransPelanggan.setText(getIntent().getStringExtra(EXTRA_LIST_TRANSAKSI_PELANGGAN));
        txtTotalHargaDetailTransPelanggan.setText(getIntent().getStringExtra(EXTRA_TOTAL_TRANSAKSI_PELANGGAN));
        txtTanggalDetailTransPelanggan.setText(getIntent().getStringExtra(EXTRA_TANGGAL_TRANSAKSI_PELANGGAN));

        btnPdfPelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("size"," "+pdfPelanggan.getWidth() +"  "+pdfPelanggan.getWidth());
                bitmap = loadBitmapFromView(pdfPelanggan, pdfPelanggan.getWidth(), pdfPelanggan.getHeight());
                createPdf();
            }
        });

    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf(){

        String namafile = getIntent().getStringExtra(EXTRA_ID_TRANSAKSI_PELANGGAN);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/"+namafile+".pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
            Log.e("DetailPelangganActivity", e.getMessage() );
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF(){

        String namafile = getIntent().getStringExtra(EXTRA_ID_TRANSAKSI_PELANGGAN);
        File file = new File("/sdcard/"+namafile+".pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName()+".provider", file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(DetailTransaksiPelangganActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(context, TransaksiPelangganActivity.class));
        finish();
    }
}
