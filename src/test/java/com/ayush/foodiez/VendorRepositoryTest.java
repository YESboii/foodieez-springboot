package com.ayush.foodiez;

import com.ayush.foodiez.TestObject.DummyObject;
import com.ayush.foodiez.model.Vendor;
import com.ayush.foodiez.repository.VendorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VendorRepositoryTest {
    private final VendorRepository vendorRepository;
    @Autowired
    public VendorRepositoryTest(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Test
    public void testThatCreate(){
        Vendor vendorA = DummyObject.vendorA();
        Vendor vendorB = DummyObject.vendorB();
        Vendor vendorC = DummyObject.vendorC();
        vendorRepository.save(vendorA);
        vendorRepository.save(vendorB);
        vendorRepository.save(vendorC);
    }
    @Test
    public void testThatUpdate(){
        Vendor vendorA = DummyObject.vendorA();
        System.out.println(vendorA);
        vendorRepository.save(vendorA);
        vendorA.setFirstName("Updated");
        vendorRepository.save(vendorA);

    }
    @Test
    public void testThatDelete(){
        vendorRepository.deleteById(4);
    }
    @Test
    @Transactional
    public void testThatFindAll(){
        Vendor vendorA = DummyObject.vendorA();
        Vendor vendorB = DummyObject.vendorB();
        Vendor vendorC = DummyObject.vendorC();
        vendorRepository.save(vendorA);
        vendorRepository.save(vendorB);
        vendorRepository.save(vendorC);
        List<Vendor> list = vendorRepository.findAll();
        System.out.println(list.get(0).getId()+" "+list.get(0).getRole());

    }
}
