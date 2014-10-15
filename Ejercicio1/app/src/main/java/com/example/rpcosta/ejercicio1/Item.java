package com.example.rpcosta.ejercicio1;


    import java.io.Serializable;


    //La clase item, con los atribuos correspondientes para cada item
    public class Item implements Serializable, Comparable{

        private static final long serialVersionUID = -7345569503145847482L;
        private String image;
        private String title;
        private String price;
        private String idItem;
        private String description;

        public Item(String image, String title, String price, String id, String descripcion)
        {
            this.image = image;
            this.title = title;
            this.price = price;
            this.idItem = id;
            this.description=descripcion;
        }

        public String getImage() {
            return image;
        }
        public void setImage(String image) {
            this.image = image;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        //Aca la logica para obtener el orden de mayor precio y menor precio para el listado
        public int compareTo(Object another) {
            // TODO Auto-generated method stub
            Item it = (Item)another;
            float price1 =Float.parseFloat(this.getPrice());
            float price2 = Float.parseFloat(it.getPrice());
            if (price1 < price2){
                return -1;
            }else if (price1 > price2){
                return 1;
            }
            return 0;
        }

        public String getIdItem() {
            return idItem;
        }

        public void setIdItem(String idItem) {
            this.idItem = idItem;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

