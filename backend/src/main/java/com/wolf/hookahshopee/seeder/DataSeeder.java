package com.wolf.hookahshopee.seeder;

import com.wolf.hookahshopee.dto.*;
import com.wolf.hookahshopee.model.Role;
import com.wolf.hookahshopee.service.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    private final PostService postService;

    private final CityService cityService;

    private final ManufacturerService manufacturerService;

    private final UserService userService;

    private final ProductService productService;

    private final ProductItemService productItemService;

    public DataSeeder(PostService postService,
                      CityService cityService,
                      ManufacturerService manufacturerService,
                      UserService userService,
                      ProductService productService,
                      ProductItemService productItemService) {
        this.postService = postService;
        this.cityService = cityService;
        this.manufacturerService = manufacturerService;
        this.userService = userService;
        this.productService = productService;
        this.productItemService = productItemService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedPosts();
        seedCities();
        seedManufacturers();
        seedUsers();
        seedProducts();
    }

    private void seedPosts() {
        PostLightDTO post = new PostLightDTO(
                "Відтепер ми також в Івано-Франківську",
                "Мережа магазинів “Залупка” - це найкращий та найдешевший спосіб придбати табак та комплектуючі для кальяну. і тд. Для Вашої уваги досутпні преміальні мрки табаку за найнижчими цінами. На нашому сайті ви можете знайти: ● Milano ● Adalya ● Dark Side ●"
        );

        postService.create(post);
        postService.updateImage(post.getName(), "Post1.png");
    }

    private void seedCities() {
        cityService.create(new CityDTO("Львів"));
        cityService.create(new CityDTO("Івано-франківськ"));
    }

    private void seedManufacturers() {
        ManufacturerDTO manufacturer1 = new ManufacturerDTO("Adalya", null);
        ManufacturerDTO manufacturer2 = new ManufacturerDTO("Jibiar", null);
        ManufacturerDTO manufacturer3 = new ManufacturerDTO("Serbetli", null);
        ManufacturerDTO manufacturer4 = new ManufacturerDTO("Milano", null);

        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);
        manufacturerService.create(manufacturer3);
        manufacturerService.create(manufacturer4);
        manufacturerService.updateImage(manufacturer1.getName(), "Adalya.png");
        manufacturerService.updateImage(manufacturer2.getName(), "Jibiar.png");
        manufacturerService.updateImage(manufacturer3.getName(), "Serbetli.png");
        manufacturerService.updateImage(manufacturer4.getName(), "Milano.png");
    }

    private void seedUsers() {
        userService.register(new UserLightDTO("380963587506", "Богдан", "Данкович", "password", "Львів"), Role.ADMIN);
        userService.register(new UserLightDTO("380970362116", "Віталій", "Боянівський", "password", "Івано-франківськ"), Role.SELLER);
        userService.register(new UserLightDTO("380958674875", "Ярослав", "Мудрий", "password", "Львів"), Role.SELLER);
        userService.register(new UserLightDTO("380965876896", "Антон", "Вовк", "password", "Івано-франківськ"), Role.CLIENT);
    }

    private void seedProducts() {
        for (int i = 0; i < 50; ++i) {
            productService.create(new ProductLightDTO("Adalya Blue Peach Mint" + i, 75, 0, "<p><strong style=\"color: rgb(61, 20, 102);\">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Milano</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Adalya </strong></p><p><strong style=\"color: rgb(61, 20, 102);\">● Dark Side </strong></p><p><br></p>", new ManufacturerDTO("Adalya", null)));
            productItemService.create(new ProductItemLightDTO(10L, i + 1L, 1L));
        }

        for (int i = 0; i < 10; ++i) {
            productService.create(new ProductLightDTO("Hip Lem (Айс Апельсин Лайм) 100 Гр" + i, 150, 15, "<p><strong style=\"color: rgb(61, 20, 102);\">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Milano</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Adalya </strong></p><p><strong style=\"color: rgb(61, 20, 102);\">● Dark Side </strong></p><p><br></p>", new ManufacturerDTO("Jibiar", null)));
            productService.create(new ProductLightDTO("Jibiar - Fresh Blue (Айс Черника) 100 гр" + i, 150, 15, "<p><strong style=\"color: rgb(61, 20, 102);\">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Milano</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Adalya </strong></p><p><strong style=\"color: rgb(61, 20, 102);\">● Dark Side </strong></p><p><br></p>", new ManufacturerDTO("Jibiar", null)));
            productItemService.create(new ProductItemLightDTO(32L, i + 51L, 2L));
        }
    }
}
