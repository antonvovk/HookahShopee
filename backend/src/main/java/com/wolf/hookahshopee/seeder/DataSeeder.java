package com.wolf.hookahshopee.seeder;

import com.wolf.hookahshopee.city.dto.CityCreateDTO;
import com.wolf.hookahshopee.city.service.CityService;
import com.wolf.hookahshopee.manufacturer.dto.ManufacturerCreateDTO;
import com.wolf.hookahshopee.manufacturer.service.ManufacturerService;
import com.wolf.hookahshopee.post.dto.PostCreateDTO;
import com.wolf.hookahshopee.post.service.PostService;
import com.wolf.hookahshopee.product.dto.ProductCreateDTO;
import com.wolf.hookahshopee.product.dto.ProductItemLightDTO;
import com.wolf.hookahshopee.product.service.ProductItemService;
import com.wolf.hookahshopee.product.service.ProductService;
import com.wolf.hookahshopee.user.dto.UserLightDTO;
import com.wolf.hookahshopee.user.model.Role;
import com.wolf.hookahshopee.user.service.UserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
        seedUsers();
        seedProducts();
    }

    private void seedPosts() {
        PostCreateDTO post1 = new PostCreateDTO(
                "Відтепер ми також в Івано-Франківську",
                "Мережа магазинів “Залупка” - це найкращий та найдешевший спосіб придбати табак та комплектуючі для кальяну. і тд. Для Вашої уваги досутпні преміальні мрки табаку за найнижчими цінами. На нашому сайті ви можете знайти: ● Milano ● Adalya ● Dark Side ●"
        );
        UUID uuid1 = postService.create(post1);
        postService.updateImage(uuid1, "Post1.png");

        PostCreateDTO post2 = new PostCreateDTO(
                "Відтепер ми також в Івано-Франківську!",
                "Відтепер ми також в Івано-Франківську!"
        );
        UUID uuid2 = postService.create(post2);
        postService.updateImage(uuid2, "Post2.png");

        PostCreateDTO post3 = new PostCreateDTO(
                "У продажі тепер доступні комплектуючі для кальянів",
                "У продажі тепер доступні комплектуючі для кальянів"
        );
        UUID uuid3 = postService.create(post3);
        postService.updateImage(uuid3, "Post3.png");
    }

    private void seedUsers() {
        UUID city1UUID = cityService.create(new CityCreateDTO("Львів"));
        UUID city2UUID = cityService.create(new CityCreateDTO("Івано-Франківськ"));

        userService.register(new UserLightDTO("380963587506", "Богдан", "Данкович", "password", city1UUID), Role.ADMIN);
        userService.register(new UserLightDTO("380970362116", "Віталій", "Боянівський", "password", city2UUID), Role.SELLER);
        userService.register(new UserLightDTO("380958674875", "Ярослав", "Мудрий", "password", city1UUID), Role.SELLER);
        userService.register(new UserLightDTO("380965876896", "Антон", "Вовк", "password", city2UUID), Role.CLIENT);
    }

    private void seedProducts() {
        ManufacturerCreateDTO manufacturer1 = new ManufacturerCreateDTO("Adalya");
        ManufacturerCreateDTO manufacturer2 = new ManufacturerCreateDTO("Jibiar");
        ManufacturerCreateDTO manufacturer3 = new ManufacturerCreateDTO("Serbetli");
        ManufacturerCreateDTO manufacturer4 = new ManufacturerCreateDTO("Milano");

        UUID mUUID1 = manufacturerService.create(manufacturer1);
        UUID mUUID2 = manufacturerService.create(manufacturer2);
        UUID mUUID3 = manufacturerService.create(manufacturer3);
        UUID mUUID4 = manufacturerService.create(manufacturer4);

        manufacturerService.updateImage(mUUID1, "Adalya.png");
        manufacturerService.updateImage(mUUID2, "Jibiar.png");
        manufacturerService.updateImage(mUUID3, "Serbetli.png");
        manufacturerService.updateImage(mUUID4, "Milano.png");

        for (int i = 0; i < 50; ++i) {
            UUID uuid = productService.create(new ProductCreateDTO("Adalya Blue Peach Mint" + i, 75L, 0L, "<p><strong style=\"color: rgb(61, 20, 102);\">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Milano</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Adalya </strong></p><p><strong style=\"color: rgb(61, 20, 102);\">● Dark Side </strong></p><p><br></p>", mUUID1));
            productService.updateImage(uuid, "Rectangle 8.png");
            productItemService.create(new ProductItemLightDTO(10L, i + 1L, 1L));
        }

        for (int i = 0; i < 10; ++i) {
            UUID uuid1 = productService.create(new ProductCreateDTO("Hip Lem (Айс Апельсин Лайм) 100 Гр" + i, 150L, 15L, "<p><strong style=\"color: rgb(61, 20, 102);\">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Milano</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Adalya </strong></p><p><strong style=\"color: rgb(61, 20, 102);\">● Dark Side </strong></p><p><br></p>", mUUID2));
            UUID uuid2 = productService.create(new ProductCreateDTO("Jibiar - Fresh Blue (Айс Черника) 100 гр" + i, 150L, 15L, "<p><strong style=\"color: rgb(61, 20, 102);\">Кращий табак в світі особливо якщо брати партії по двійній ціні</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Milano</strong></p><p><strong style=\"color: rgb(61, 20, 102);\"> ● Adalya </strong></p><p><strong style=\"color: rgb(61, 20, 102);\">● Dark Side </strong></p><p><br></p>", mUUID2));
            productService.updateImage(uuid1, "Rectangle 8.png");
            productService.updateImage(uuid2, "Rectangle 8.png");
            productItemService.create(new ProductItemLightDTO(32L, i + 51L, 2L));
        }
    }
}
