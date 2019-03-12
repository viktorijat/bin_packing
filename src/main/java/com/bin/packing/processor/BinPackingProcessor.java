package com.bin.packing.processor;

import com.bin.packing.model.Activity;
import com.bin.packing.repository.ActivityRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class BinPackingProcessor {

    @Autowired
    ActivityRepository activityRepository;

//
//    public static void binPacking(Iterable<Activity> a, Duration size, int n)
//    {
//        int binCount=1;
//        Duration s = size;
//        for(int i=0; i<n; i++)
//        {
//            if(s - a[i] > 0)
//            {
//                s -= a[i];
//                continue;
//            }
//            else
//            {
//                binCount++;
//                s = size;
//                i--;
//            }
//        }
//
//        System.out.println("Number of bins required: "+binCount);
//    }

    public void calculate() {


//        List<Activity> all = activityRepository.findAll();
//        List<Activity> myList = new ArrayList<>(((List<Activity>) all).size());
//        all.forEach(myList::add);
//        System.out.println(myList);


//        binPacking(all, Duration.ofHours(8), 22);
//
//        List<Team> teams = Arrays.asList(new Team(), new Team());


    }
}
