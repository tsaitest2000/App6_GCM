package lab.app_drawer;

public class BMW {

   private String type;
   private String name;
   private String imageUrl;

   public BMW(String type, String name, String imageUrl) {
      this.imageUrl = imageUrl;
      this.name = name;
      this.type = type;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

}
