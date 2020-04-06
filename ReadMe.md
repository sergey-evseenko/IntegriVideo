#CheckList:
1. Registration
2. Login
3. Logout
3. Create project
4. Open project page and verify elements on the page
4. Update project
5. Add component (each type)
6. Open component page and verify elements on the page
6. Update component
7. Open billing page
9. Add new card. Add one more card.
9. Make card as default
10. Add/remove domain

#Maven Commands (Home work #14)
1. Обновить версии бибилитек в проекте:
mvn versions:use-latest-versions

2. Запустить тесты. Методы sendMessage и verifyInviteLink из класса ChatTests
mvn -Dtest=ChatTests#sendMessage+verifyInviteLink test

3. Запуск файла pom.xml, находяшегося в директории отлично от текущей
mvn -Dtest=ChatTests#sendMessage test -f IdeaProjects/IntegriVideo/pom.xml

4. Пробросить параметр из mvn command line внутрь теста
-Dtest=ChatTests#runTest test -DuserName=sergey_evseenko

    @Test
    public void runTest() {
        System.out.println(System.getProperty("userName"));
    }
