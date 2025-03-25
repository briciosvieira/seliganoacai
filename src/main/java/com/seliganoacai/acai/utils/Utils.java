package com.seliganoacai.acai.utils;

import com.seliganoacai.acai.entity.RelationsOrdersProduct;

import java.util.List;
import java.util.Random;

public class Utils {

    public  static String generateTicket(){
        String caracter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int maxi = 6;
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < maxi; i++) {
            int index = random.nextInt(caracter.length());
            result.append(caracter.charAt(index));
        }

        return result.toString();
    }

    public static double calculateTotalValue(List<RelationsOrdersProduct> items) {
        return items.stream()
                .mapToDouble(map -> map.getProduct().getValue() * map.getQuantity())
                .sum();
    }
}
