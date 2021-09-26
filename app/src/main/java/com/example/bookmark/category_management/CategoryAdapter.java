//package com.example.bookmark.category_management;
//
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.bookmark.R;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.FirebaseDatabase;
//import com.orhanobut.dialogplus.DialogPlus;
//import com.orhanobut.dialogplus.ViewHolder;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//public class CategoryAdapter extends FirebaseRecyclerAdapter<CategoryModule,CategoryAdapter.myViewHolder> {
//
//    /**
//     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
//     * {@link FirebaseRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */package com.example.bookmark.category_management;
//
//    public class MainModel {
//
//        String description,discountPrice,image,price,product,title;
//
//        MainModel(){
//
//        }
//
//        public MainModel(String description, String discountPrice, String image, String price, String product, String title) {
//            this.description = description;
//            this.discountPrice = discountPrice;
//            this.image = image;
//            this.price = price;
//            this.product = product;
//            this.title = title;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public String getDiscountPrice() {
//            return discountPrice;
//        }
//
//        public void setDiscountPrice(String discountPrice) {
//            this.discountPrice = discountPrice;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//
//        public void setPrice(String price) {
//            this.price = price;
//        }
//
//        public String getProduct() {
//            return product;
//        }
//
//        public void setProduct(String product) {
//            this.product = product;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//    }
//
//    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<CategoryModule> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CategoryModule model) {
//
//        holder.product.setText(model.getProduct());
//
//
//
//        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.product.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.update_popupcat))
//                        .setExpanded(true,1000)
//                        .create();
//               // dialogPlus.show();
//                View view = dialogPlus.getHolderView();
//
//                EditText category = view.findViewById(R.id.txtCAT);
//                EditText Description = view.findViewById(R.id.txtDES);
//
//                Button btnUpdate = view.findViewById(R.id.btnUpdate);
//
//                //set data to edittext
//                category.setText(model.getProduct());
//
//
//                dialogPlus.show();
//
//                btnUpdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Map<String,Object> map= new HashMap<>();
//                        map.put("category",category.getText().toString());
//                        map.put("Description",Description.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("Categories")
//                                .child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(holder.category.getContext(), "Updated Sucessfully", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure( Exception e) {
//                                        Toast.makeText(holder.category.getContext(), "Error while Updating", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                });
//                    }
//                });
//
//            }
//        });
//
//        //Delete Category
//      holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              AlertDialog.Builder builder = new AlertDialog.Builder(holder.category.getContext());
//
//              builder.setTitle("Are you sure ?");
//              builder.setMessage("Deleted data can't be Undo ");
//
//              builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                  @Override
//                  public void onClick(DialogInterface dialog, int which) {
//
//                      FirebaseDatabase.getInstance().getReference().child("Categories")
//                              .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
//
//                  }
//              });
//
//              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                  @Override
//                  public void onClick(DialogInterface dialog, int which) {
//                      Toast.makeText(holder.category.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//                  }
//              });
//
//              builder.show();
//
//          }
//      });
//
//
//
//    }
//
//    @NonNull
//    @Override
//    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_one,parent,false);
//        return new myViewHolder(view);
//    }
//
//    class myViewHolder extends RecyclerView.ViewHolder{
//
//        TextView product;
//
//        Button btnEdit,btnDelete;
//
//        public myViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//             category = (TextView)itemView.findViewById(R.id.txtname1);
//             Description = (TextView)itemView.findViewById(R.id.txtname2);
//
//            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
//            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
//
//        }
//    }
//
//}
