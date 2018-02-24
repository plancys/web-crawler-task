# Web crawler

Celem zadania jest zaimplementowanie mechanizmu który będzie potrafił pobrać stronę WWW na dysk naszego komputera.
Zadanie zawiera już podstawowy design: 
1. `PageExtractor` - dostaje adres strony i pobiera jej źródło (body) zapisując w obiekcie `Page`.
2. `WebCrawler` - dostaje obiekt `Page`, analizuje jakie linki wychodzą ze tej strony, następnie pobiera te linki opakowując je w kolekcje typu `Page`.
3. `PageSnapshotCreator` - tworzy strukturę strony (a właściwie połączeń między podstronami) zapisujać je w obiekcie typu `PageSnapshot`.
4. `PageDownloader` - pobiera stronę dostając ją w obiekcie typu `PageSnapshot` do zadanego folderu.


### TODOs
1. Na początek wykonaj fork tego repozytorium - czyli skopiowanie zawartości tego repozytorium i wrzucenie go do własnego:
 * [tutaj link jak to zrobić za pomocą git huba](https://guides.github.com/activities/forking/)
  * [tutaj link jak to zrobić za pomocą gita](https://help.github.com/articles/fork-a-repo/).

2. Zaimplementuj interfejsy którym brakuje implementacji - zastanów się od którego zacząć.
