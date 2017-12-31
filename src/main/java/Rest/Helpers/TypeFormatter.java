package Rest.Helpers;

import Rest.Model.AuctionModel;
import Rest.Model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

public class TypeFormatter {

    public static AuctionModel[] listToArray(List<AuctionModel> list){

        AuctionModel[] result = new AuctionModel[list.size()];


        for(int i =0; i<list.size(); i++){

            if(list.get(i).getFiles() != null || list.get(i).getFiles().size() != 0){
                try {
                    PhotoModel tmp = list.get(i).getFiles().get(0);
                    List<PhotoModel> tmpArray = new ArrayList<>();
                    tmpArray.add(tmp);
                    list.get(i).setFiles(tmpArray);
                }catch (Exception e){
                    List<PhotoModel> tmpArray = new ArrayList<>();
                    tmpArray.add(new PhotoModel());
                    list.get(i).setFiles(tmpArray);
                }
            }else{
                List<PhotoModel> tmpArray = new ArrayList<>();
                tmpArray.add(new PhotoModel());
                list.get(i).setFiles(tmpArray);
            }
            result[i] = list.get(i);
        }

        return result;
    }

}
