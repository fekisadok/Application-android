package com.example.sam.application_final;

/**
 * Created by sam on 10/03/2018.


public class MicropostAdapter extends BaseAdapter {
    private Context oContext;
    private LayoutInflater oInflater;
    private ArrayList<Micropost> listeMicroposts;
    private ImageView oImage;

    public MicropostAdapter(Context pContext, ArrayList<Micropost> plisteMicroposts){
        this.oContext = pContext;
        this.listeMicroposts= plisteMicroposts;
        this.oInflater= LayoutInflater.from(pContext);
    }

    @Override
    public int getCount() {
        return this.listeMicroposts.size();
    }

    @Override
    public Object getItem(int pPosition) {
        return this.listeMicroposts.get(pPosition);
    }

    @Override
    public long getItemId(int pPosition) {
        return pPosition;
    }

    @Override
    public View getView(int pPosition, View pConvertView, ViewGroup pParent) {

        TableLayout tLayoutItem;
        String lChemin = null;
        Bitmap bm;

        if(pConvertView==null){
            tLayoutItem=(TableLayout) oInflater.inflate(R.layout.micropost, pParent, false);

        }else {
            tLayoutItem=(TableLayout) pConvertView;
        }
        TextView txtMicropost = (TextView) tLayoutItem.findViewById(R.id.txtMicropost);
        txtMicropost.setText(this.listeMicroposts.get(pPosition).getContenu().trim());

        lChemin="http://192.168.1.16/"+ this.listeMicroposts.get(pPosition).getAvatar().trim();
        bm = downloadImage(lChemin);
        this.oImage= (ImageView) tLayoutItem.findViewById(R.id.ImgMicropost);
        this.oImage.setImageBitmap(bm);
        return tLayoutItem;
    }
    private Bitmap downloadImage(String pChemin){
        Bitmap bitmap= null;
        try{
            URL urlImage= new URL(pChemin);
            HttpURLConnection connection= (HttpURLConnection) urlImage.openConnection();
            InputStream inputStream= connection.getInputStream();
            bitmap= BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException muex) {
            muex.printStackTrace();
        }catch (IOException ioex){
            ioex.printStackTrace();
        }
        return bitmap;
    }
}
 */