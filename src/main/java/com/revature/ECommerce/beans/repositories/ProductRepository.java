package com.revature.ECommerce.beans.repositories;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class ProductRepository implements HibernateRepository<Product> {
    private HibernateManager hibernateManager;
    boolean running = false;
    private Session session;

    @Autowired
    public ProductRepository(HibernateManager hibernateManager){
        this.hibernateManager=hibernateManager;
    }

    //works
    @Override
    public Product save(Product product) {
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        return product;
    }

    //works
    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = session.createQuery("FROM Product");
        return query.getResultList();
    }

    //works
    @Override
    public Product getById(Integer id) {
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE id = :product_id", Product.class);
        query.setParameter("product_id", id);

        return query.getSingleResult();
    }

    //test
//    public Product getByName (String name){
//        TypedQuery<Product> query = session.createQuery("FROM Product WHERE name = :product_name", Product.class);
//        query.setParameter("product_name", name);
//        Product product = query.getSingleResult();
//
//        return product;
//    }

    //works
    //Returns a list of products based on a selected category.
    public List<Product> getByCategory(String category){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE category = :category", Product.class);
        query.setParameter("category", category);

        return query.getResultList();
    }

    //works
    //Returns a list of products based on availability.
    //The two status strings we are using are, "In Stock" and "Out of Stock".
    public List<Product> getByStatus(String status){
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE status = :status", Product.class);
        query.setParameter("status", status);

        return query.getResultList();
    }

    //test
    public void deleteById(Integer product_id) {
        Transaction tx = session.beginTransaction();
        TypedQuery<Product> query = session.createQuery("Delete Product WHERE product_id = :product_id");
        query.setParameter("product_id", product_id);
        query.executeUpdate();
        tx.commit();
    }

    public void deleteProduct(Product product){
        Transaction tx = session.beginTransaction();
        session.remove(session.get(Product.class, product.getProductId()));
        tx.commit();
    }

    //Works through Postman
    //Updates a product quantity by product id.
    @Override
    public Product update(Product product) {

            Product updateProduct = this.getById(product.getProductId());
            updateProduct.setProductName(product.getProductName());
            updateProduct.setProductPrice(product.getProductPrice());
            updateProduct.setProductQuantity(product.getProductQuantity());
            updateProduct.setProductDescription(product.getProductDescription());
            updateProduct.setProductImage(product.getProductImage());
            updateProduct.setProductStatus(product.getProductStatus());
            updateProduct.setProductCategory(product.getProductCategory());

            this.save(updateProduct);
            return updateProduct;
    }

    public List<Product> sortPriceDESC() {
        TypedQuery<Product> query = session.createQuery("FROM Product ORDER BY price DESC", Product.class);
        return query.getResultList();
    }
    public List<Product> sortPriceASC() {
        TypedQuery<Product> query = session.createQuery("FROM Product ORDER BY price ASC", Product.class);
        return query.getResultList();
    }
    public List<Product> sortProductNameASC() {
        TypedQuery<Product> query = session.createQuery("FROM Product ORDER BY productName ASC", Product.class);
        return query.getResultList();
    }

    public List<Product> sortProductNameDESC() {
        TypedQuery<Product> query = session.createQuery("FROM Product ORDER BY productName DESC", Product.class);
        return query.getResultList();
    }

    @Override
    public void start() {
        this.session=hibernateManager.getSession();
        running=true;
    }

    @Override
    public void stop() {
        running=false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
