package com.grimaldo.support.packs;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.grimaldo.support.packs");

        noClasses()
            .that()
            .resideInAnyPackage("com.grimaldo.support.packs.service..")
            .or()
            .resideInAnyPackage("com.grimaldo.support.packs.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.grimaldo.support.packs.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
