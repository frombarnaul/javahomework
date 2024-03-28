import java.util.*;

public class Program {
    public static void main(String[] args) {
        // Создаем множество для хранения ноутбуков
        Set<Notebook> notebooks = createNotebooks();

        // Запрашиваем критерии фильтрации у пользователя
        Map<String, Object> filterCriteria = getFilterCriteria();

        // Отфильтровываем ноутбуки и выводим результат
        filterAndPrintFilteredNotebooks(notebooks, filterCriteria);
    }

    // Метод для создания множества ноутбуков
    private static Set<Notebook> createNotebooks() {
        Set<Notebook> notebooks = new HashSet<>();
        // Добавьте ваш код для создания ноутбуков здесь
        // Пример:
        notebooks.add(new Notebook("Brand1", 8, 512, "Windows", "Black"));
        notebooks.add(new Notebook("Brand2", 16, 1024, "MacOS", "Silver"));
        notebooks.add(new Notebook("Brand3", 8, 256, "Linux", "Blue"));
        return notebooks;
    }

    // Метод для запроса критериев фильтрации у пользователя
    private static Map<String, Object> getFilterCriteria() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filterCriteria = new HashMap<>();

        System.out.println("Введите критерии фильтрации (например: '1,2,3'):");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        String[] criteriaNumbers = scanner.nextLine().split(",");
        for (String num : criteriaNumbers) {
            switch (num.trim()) {
                case "1":
                    System.out.print("Введите минимальное значение ОЗУ (в ГБ): ");
                    int minRam = scanner.nextInt();
                    filterCriteria.put("ram", minRam);
                    break;
                case "2":
                    System.out.print("Введите минимальный объем ЖД (в ГБ): ");
                    int minStorage = scanner.nextInt();
                    filterCriteria.put("storage", minStorage);
                    break;
                case "3":
                    System.out.print("Введите операционную систему: ");
                    String os = scanner.next();
                    filterCriteria.put("os", os);
                    break;
                case "4":
                    System.out.print("Введите цвет: ");
                    String color = scanner.next();
                    filterCriteria.put("color", color);
                    break;
                default:
                    System.out.println("Неверный критерий: " + num);
            }
        }

        scanner.close();

        return filterCriteria;
    }

    // Метод для фильтрации ноутбуков и вывода отфильтрованных результатов
    private static void filterAndPrintFilteredNotebooks(Set<Notebook> notebooks, Map<String, Object> filterCriteria) {
        Set<Notebook> filteredNotebooks = new HashSet<>();
        for (Notebook notebook : notebooks) {
            boolean meetsCriteria = true;
            for (Map.Entry<String, Object> entry : filterCriteria.entrySet()) {
                String criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case "ram":
                        if (notebook.getRam() < (int) value) {
                            meetsCriteria = false;
                        }
                        break;
                    case "storage":
                        if (notebook.getStorage() < (int) value) {
                            meetsCriteria = false;
                        }
                        break;
                    case "os":
                        if (!notebook.getOs().equalsIgnoreCase((String) value)) {
                            meetsCriteria = false;
                        }
                        break;
                    case "color":
                        if (!notebook.getColor().equalsIgnoreCase((String) value)) {
                            meetsCriteria = false;
                        }
                        break;
                }
            }
            if (meetsCriteria) {
                filteredNotebooks.add(notebook);
            }
        }

        System.out.println("Ноутбуки, отвечающие условиям фильтра:");
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }
    }
}
