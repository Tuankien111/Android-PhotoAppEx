package tuankien.st001.android_photoappex;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PhotoData {
//    public static ArrayList<Photo> generatePhotoData(){
//        ArrayList<Photo> photos= new ArrayList<>();
//         photos.add(new Photo(0, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png" ,"Bulbasaur #0001","There is a plant seed on its back right from the day this Pokémon is born. The seed slowly grows larger."));
//       photos.add(new Photo(1, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png" ,"Charmander #0004","It has a preference for hot things. When it rains, steam is said to spout from the tip of its tail."));
//       photos.add(new Photo(2, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png" ,"Squirtle #0007","When it retracts its long neck into its shell, it squirts out water with vigorous force."));
//       photos.add(new Photo(3, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png" ,"Pikachu #0001","When it is angered, it immediately discharges the energy stored in the pouches in its cheeks."));
//       photos.add(new Photo(4, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/026.png" ,"Raichu #0026","Its tail discharges electricity into the ground, protecting it from getting shocked."));
//       photos.add(new Photo(5, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/810.png" ,"Grookey #0810","There is a plant seed on its back right from the day this Pokémon is born. The seed slowly grows larger."));
//       photos.add(new Photo(6, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/813.png" ,"Scorbunny #0813","There is a plant seed on its back right from the day this Pokémon is born. The seed slowly grows larger."));
//       return photos;
//    }
//    public static Photo getphotoFromId(int id){
//        ArrayList<Photo> phs = generatePhotoData();
//        for (int i=0; i< phs.size();i++){
//            if(phs.get(i).getId() == id){
//                return phs.get(i);
//            }
//        }
//        return null;
//    }

    private static Context context;

    public static void init(Context context) {
        PhotoData.context = context;
    }

    public static ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String jsonString = loadJSONFromAsset("Photodata.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String source = jsonObject.getString("source_photo");
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                photos.add(new Photo(id, source, title, description));
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photos;
    }

    private static String loadJSONFromAsset(String fileName) throws IOException {
        String jsonString;
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        jsonString = new String(buffer, "UTF-8");
        return jsonString;
    }
    public static Photo getPhotoById(int id) {
        Photo photo = null;

        try {
            String jsonString = loadJSONFromAsset("Photodata.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int photoId = jsonObject.getInt("id");

                if (photoId == id) {
                    String source = jsonObject.getString("source_photo");
                    String title = jsonObject.getString("title");
                    String description = jsonObject.getString("description");
                    photo = new Photo(id, source, title, description);
                    break;
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photo;
    }
}