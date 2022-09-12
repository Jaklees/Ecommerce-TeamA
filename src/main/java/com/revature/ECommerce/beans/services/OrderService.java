package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.OrderRepository;
import com.revature.ECommerce.entities.Order;
import com.revature.ECommerce.entities.Sale;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.exceptions.EmptyCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository oRepo;
    private UserService uServ;
    private SaleService sServ;

    @Autowired
    public OrderService(OrderRepository oRepo, UserService uServ, SaleService sServ){
        this.uServ=uServ;
        this.oRepo=oRepo;
        this.sServ=sServ;
    }

    public Order addToOrder(Order order, Sale sale) {
        //
        //sServ.save(sale);
        if(oRepo.orderExists(order)){
            List<Sale> tempList=new ArrayList<>();
            tempList=order.getSaleList();
            //sale.setOrder(order);
            tempList.add(sale);
            order.setSaleList(tempList);
            return oRepo.update(order);
        }else{

            //order=oRepo.save(order);
            List<Sale> tempList=new ArrayList<>();
            if(order.getSaleList()!=null) {
                tempList = order.getSaleList();
            }
            //sale.setOrder(order);
            tempList.add(sale);
            order.setSaleList(tempList);
            //order=oRepo.update(order);
            return order;
        }
    }

    public Order removeFromOrder(Order order, Sale sale) throws Exception {
        List<Sale>checker=new ArrayList<>();
        checker=order.getSaleList();
        boolean checker2= order.getSaleList().contains(sale);
        if (checker != null && !checker.isEmpty()) {
            for(int i=0; i<checker.size(); i++){
                if(checker.get(i).getSaleId().equals(sale.getSaleId())) {
                    checker.remove(i);
                    order.setSaleList(checker);
                    sServ.delete(sale);
                    return order;
                }

            }
        }else if(checker.isEmpty()){
            throw new Exception("This order has nothing to remove");
        }
        throw new Exception("There is no such Sale");
    }


    public Order checkOut(User user, Order order){
        if(order.getSaleList()==null || order.getSaleList().isEmpty()){
            throw new EmptyCartException("There's nothing to checkout! This cart is empty");
        }
        if(user.getUserId()==null|| user.getUserId()==0) {
            //uServ.save(user);
            throw new RuntimeException("Only Registered users can purchase items!");
        }else{
            List<Order>templist= new ArrayList<>();
            if(user.getListOfOrders()!=null) {
                templist = user.getListOfOrders();
            }
            templist.add(order);
            user.setListOfOrders(templist);
            uServ.update(user);
        }
        order.setUser(user);
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp=Timestamp.valueOf(now);
        for(Sale s : order.getSaleList()){
            s.setDateOfPurchase(timestamp);
            sServ.updateSale(s);
        }
        oRepo.save(order);

        return order;

    }

    public void delete(Order order){
        for(Sale s : order.getSaleList()){
            sServ.delete(s);
        }
        if(order.getOrderId()==null||order.getOrderId()==0){
            throw new RuntimeException("No Such Order exists");
        }else {
            oRepo.deleteOrder(order);
        }
    }

    public Order getById(Integer id){
        return oRepo.getById(id);
    }

    public List<Order> getByUser(User user){
        return oRepo.getByUser(user);
    }

    public List<Order> getAll(){
        return oRepo.getAll();
    }

    public Order update(Order order){
        return oRepo.update(order);
    }

    public List<Order> getByUserId(Integer id){
        return oRepo.getByUserId(id);
    }
}
