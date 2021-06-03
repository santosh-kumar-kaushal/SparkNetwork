# SparkNetwork - Personality Test
- Spark Network Assignment - MVVM clean + Room + Koin + Navigation + Rx + Junit Mokito + Ui Expresso.
- Demo app allows user to answer questions based on category.

### Clean Architecture
- Presentation
- Domain
- Data

### Communication between layers

1. UI calls method from ViewModel.
2. ViewModel executes Use case.
3. Use case has both local and remote data souce.
4. Each Data source returns data from local or remote.
5. Data flows back to the UI where we display categories and questions.

### At a glance:

- Created a list of categories.
- Once user clicks on first category he/she will be able to answer the questions.
- When user answers the last question from a category, next category will be available for him.
- No category and question is skippable.
- Thanks note is added once user answers all the questions from each category.
- Supported orientation change.
- Junit tests Added.
- Ui tests added.


