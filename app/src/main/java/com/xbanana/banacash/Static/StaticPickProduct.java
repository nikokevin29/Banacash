package com.xbanana.banacash.Static;

import com.xbanana.banacash.DAO.DetailTransaksiDAO;
import com.xbanana.banacash.DAO.ProdukDAO;

import java.util.ArrayList;
import java.util.List;

public class StaticPickProduct {
    public static List<ProdukDAO> selectProduct;
    public static List<DetailTransaksiDAO> details = new ArrayList<DetailTransaksiDAO>();
    public static Double totals;
}
