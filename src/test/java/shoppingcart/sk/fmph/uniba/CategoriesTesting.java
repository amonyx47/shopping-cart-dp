package shoppingcart.sk.fmph.uniba;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import shoppingcart.sk.fmph.uniba.entities.Category;
import shoppingcart.sk.fmph.uniba.repository.CategoryRepository;

import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoriesTesting {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void tryAddingOneCategory(){
        Category category = new Category();
        category.setName("CategoryName100");
        category.setDescription("This is category desc 100.");
        category.setDisabled(false);

        testEntityManager.persist(category);
        testEntityManager.flush();

        Category categoryFound = categoryRepository.getByName(category.getName());

        assertEquals("CategoryName100", categoryFound.getName());
    }

    @Test
    public void tryAddingHundredCategories(){
        for(int i = 0; i < 100; i++) {
            Category category = new Category();
            category.setName("CategoryName100" + i);
            category.setDescription("This is category desc 100" + i + ".");
            category.setDisabled(false);

            testEntityManager.persist(category);
            testEntityManager.flush();

            Category categoryFound = categoryRepository.getByName(category.getName());

            assertEquals("CategoryName100" + i, categoryFound.getName());
        }
    }

    @Test
    public void disableOneRandomCategory(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 98 + 1);
        Category category = categoryRepository.getByName("Cat" + randomNum);
        category.setDisabled(true);

        testEntityManager.persist(category);
        testEntityManager.flush();

        Category category1 = categoryRepository.getByName("Cat" + randomNum);

        assertTrue(category1.isDisabled());
    }

    @Test
    public void findRandomCategoryInKnownRangeIsActive(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 98 + 1);
        Category category = categoryRepository.getByName("Cat" + randomNum);
        assertFalse(category.isDisabled());
    }

}
