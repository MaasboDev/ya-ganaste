# ya-ganaste
## Bank Information Retrieval and Display in Jetpack Compose

This is a code test in Jetpack Compose that allows a user to retrieve bank information from an API and displays it in a list.  

### Technologies Used

    Kotlin
    Android Studio
    Jetpack Compose
    MVVM Architecture
    Coil image loading library
    Room Persistence Library

### Installation

    Clone the repository or download the ZIP file.
    Open the project in Android Studio.
    Build and run the project on an emulator or physical device.

### Code Structure

The code consists of several components:

    BankDbModel.kt - This data class represents a bank with its attributes (name, description, age, and url) to store in the local database.

    BankDao.kt - This interface defines the methods for accessing the bank data from the Room database.

    AppDatabase.kt - This class is responsible for creating the Room database and providing access to the BankDao.

    BankRepository.kt - This class provides a layer of abstraction between the ViewModel and the database.
    
    BankDbModelMapper.kt - This file contains all the mapper functions to convert between remote, domain and database bank representations.
    
    LocalBankDataSource.kt - This class is responsible to call the BankDao methods to store and retrieve data locally on the device.
    
    Bank.kt - One in the remote/model folder and another in the domain/model folder, they represent the remote and domain bank representations.
    
    BankApiService.kt - This interface defines the retrofit @GET method to retrieve the banks information from the API.
    
    RemoteBankDataSource.kt - This class is responsible to fire the BankApiService call retrieving data from the API and storing it on the local database.
    
    LocalDataModule.kt - This object provides the AppDatabase instance to be injected where is needed.
    
    BankApiModule.kt - This object provides the BankApiService and retrofit instances to be injected where is needed.
    
    CoroutinesDispatcherModule.kt - This object provides a coroutine dispatcher to be injected where is needed.

    MainViewModel.kt - This class implements the ViewModel of the application. It communicates with the repository to retrieve and update the bank data.

    MainActivity.kt - This class is the main activity of the application. It calls the Jetpack Compose UI.
    
    MainScreen.kt - This file is the main composable of the application. Initializes the ViewModel and draws the data on the screen.

### Conclusion

This code test demonstrates how to retrieve bank information from an API and display it in a list using Jetpack Compose, ViewModel, and Room Persistence Library. The code can be further improved by adding additional features and functionality.
