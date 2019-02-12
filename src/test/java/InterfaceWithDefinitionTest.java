import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterfaceWithDefinitionTest {
    @Test
    public void shouldGetFromMethodWithDefinition() {
        InterfaceWithDefinition interfaceWithDefinitionImpl = new InterfaceWithDefinitionImpl();

        assertThat(interfaceWithDefinitionImpl.methodWithDefinition()).isEqualTo("Hi..!! Am a method with definition");
    }

    @Test
    public void shouldGetFromMethodWithoutDefinition() {
        InterfaceWithDefinition interfaceWithDefinitionImpl = new InterfaceWithDefinitionImpl();

        assertThat(interfaceWithDefinitionImpl.methodWithoutDefinition())
                .isEqualTo("Hi, I'am method without definition");
    }

    @Test
    public void shouldGetFromClassOrInterface() {
        ImplInterfaceAndClass implInterfaceAndClass = new ImplInterfaceAndClass();

        assertThat(implInterfaceAndClass.methodWithoutDefinition()).isEqualTo("Hi, I'am method without definition from class");
    }

    @Test
    public void staticMethodInterface() {
        assertThat(InterfaceWithStaticMethod.staticMethod()).isEqualTo("Hey I am static Method");
    }

    interface InterfaceWithDefinition {
        String methodWithoutDefinition();

        default String methodWithDefinition()
        {
            return "Hi..!! Am a method with definition";
        }
    }

    class InterfaceWithDefinitionImpl implements InterfaceWithDefinition {
        @Override
        public String methodWithoutDefinition() {
            return "Hi, I'am method without definition";
        }
    }

    interface InterfaceToCauseDreadedDiamond {
        default String methodWithDefinition()
        {
            return "Hi..!! Am a method with definition in another interface";
        }
    }

    class DreadedDiamond implements InterfaceToCauseDreadedDiamond, InterfaceWithDefinition {

        @Override
        public String methodWithoutDefinition() {
            return null;
        }

        //fored to override due to dreaded diamond problem
        @Override
        public String methodWithDefinition() {
            return null;
        }
    }

    class ClassSameAsInterface {
        public String methodWithDefinition() {
            return "Hi..!! Am a method from class";
        }
    }

    class ImplInterfaceAndClass extends ClassSameAsInterface implements InterfaceWithDefinition  {

        @Override
        public String methodWithoutDefinition() {
            return "Hi, I'am method without definition from class";
        }
    }

//    interface InterfaceWithDefaultMethods {
//        //not allow default methd override
//        default boolean equals(Object object) {
//            return true;
//        }
//    }

    interface InterfaceWithStaticMethod {
        static String staticMethod() {
            return "Hey I am static Method";
        }
    }


}
