package com.example.pab_tugas_w6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pab_tugas_w6.ui.theme.PAB_TUGAS_W6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PAB_TUGAS_W6Theme {
                PAB_TUGAS_W6App()
            }
        }
    }
}

// Model Data Buku
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val category: String,
    val rating: Double,
    val pages: Int,
    val publishedYear: Int,
    val description: String,
    val coverGradientStart: Color,
    val coverGradientEnd: Color,
    val isWishlisted: Boolean = false
)

// Data Awal Katalog Buku
val initialBooks = listOf(
    Book(
        id = 1,
        title = "Laskar Pelangi",
        author = "Andrea Hirata",
        category = "Novel",
        rating = 4.8,
        pages = 529,
        publishedYear = 2005,
        description = "Kisah tentang perjuangan 10 anak di Desa Gantung, Belitung, yang menempuh pendidikan di sekolah Muhammadiyah dengan segala keterbatasan namun kaya akan semangat dan mimpi.",
        coverGradientStart = Color(0xFF1E88E5),
        coverGradientEnd = Color(0xFF1565C0),
        isWishlisted = true
    ),
    Book(
        id = 2,
        title = "Bumi",
        author = "Tere Liye",
        category = "Petualangan",
        rating = 4.7,
        pages = 440,
        publishedYear = 2014,
        description = "Petualangan Raib, seorang remaja perempuan yang bisa menghilang, bersama dua sahabatnya Ali dan Seli menjelajahi dunia paralel penuh misteri.",
        coverGradientStart = Color(0xFF2E7D32),
        coverGradientEnd = Color(0xFF1B5E20),
        isWishlisted = false
    ),
    Book(
        id = 3,
        title = "Filosofi Teras",
        author = "Henry Manampiring",
        category = "Self-Improvement",
        rating = 4.9,
        pages = 346,
        publishedYear = 2018,
        description = "Penerapan filsafat Yunani-Romawi kuno (Stoisisme) yang dikemas secara relevan dan praktis untuk mengatasi emosi negatif dan stres dalam kehidupan modern.",
        coverGradientStart = Color(0xFF6A1B9A),
        coverGradientEnd = Color(0xFF4A148C),
        isWishlisted = true
    ),
    Book(
        id = 4,
        title = "Atomic Habits",
        author = "James Clear",
        category = "Self-Improvement",
        rating = 4.9,
        pages = 320,
        publishedYear = 2018,
        description = "Panduan praktis dan terbukti untuk membentuk kebiasaan baik dan menghilangkan kebiasaan buruk dengan perubahan-perubahan kecil 1% setiap harinya.",
        coverGradientStart = Color(0xFFEF6C00),
        coverGradientEnd = Color(0xFFE65100),
        isWishlisted = false
    ),
    Book(
        id = 5,
        title = "Laut Bercerita",
        author = "Leila S. Chudori",
        category = "Sejarah",
        rating = 4.8,
        pages = 379,
        publishedYear = 2017,
        description = "Novel sejarah bertema aktivis mahasiswa era 1998 yang diculik dan dihilangkan secara paksa, serta ketabahan keluarga yang ditinggalkan.",
        coverGradientStart = Color(0xFF00838F),
        coverGradientEnd = Color(0xFF006064),
        isWishlisted = false
    ),
    Book(
        id = 6,
        title = "Clean Code",
        author = "Robert C. Martin",
        category = "Teknologi",
        rating = 4.6,
        pages = 464,
        publishedYear = 2008,
        description = "Panduan komprehensif bagi para pengembang perangkat lunak untuk menulis kode yang bersih, mudah dibaca, dan mudah dirawat.",
        coverGradientStart = Color(0xFF37474F),
        coverGradientEnd = Color(0xFF212121),
        isWishlisted = false
    ),
    Book(
        id = 7,
        title = "Sapiens",
        author = "Yuval Noah Harari",
        category = "Sains & Sejarah",
        rating = 4.7,
        pages = 512,
        publishedYear = 2011,
        description = "Penjelajahan sejarah umat manusia dari zaman batu hingga revolusi kecerdasan buatan, menjelaskan bagaimana Homo Sapiens bisa mendominasi bumi.",
        coverGradientStart = Color(0xFFD84315),
        coverGradientEnd = Color(0xFFBF360C),
        isWishlisted = false
    ),
    Book(
        id = 8,
        title = "Seni Bodo Amat",
        author = "Mark Manson",
        category = "Self-Improvement",
        rating = 4.5,
        pages = 246,
        publishedYear = 2016,
        description = "Pendekatan filosofis yang lugas dan berani untuk fokus pada hal-hal yang benar-benar penting dan mengabaikan hal-hal sepele dalam hidup.",
        coverGradientStart = Color(0xFFC62828),
        coverGradientEnd = Color(0xFFB71C1C),
        isWishlisted = false
    )
)

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Katalog", R.drawable.ic_home),
    WISHLIST("Wish List", R.drawable.ic_favorite),
    PROFILE("Profil", R.drawable.ic_account_box),
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun PAB_TUGAS_W6App() {
    var books by remember { mutableStateOf(initialBooks) }
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    var selectedBookForDetail by remember { mutableStateOf<Book?>(null) }

    val onToggleWishlist: (Book) -> Unit = { targetBook ->
        books = books.map { book ->
            if (book.id == targetBook.id) {
                book.copy(isWishlisted = !book.isWishlisted)
            } else {
                book
            }
        }
        // Update selected book detail state as well if open
        if (selectedBookForDetail?.id == targetBook.id) {
            selectedBookForDetail = selectedBookForDetail?.let {
                it.copy(isWishlisted = !it.isWishlisted)
            }
        }
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            painter = painterResource(destination.icon),
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = { currentDestination = destination }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (currentDestination) {
                    AppDestinations.HOME -> HomeScreen(
                        books = books,
                        onBookClick = { book -> selectedBookForDetail = book },
                        onToggleWishlist = onToggleWishlist
                    )

                    AppDestinations.WISHLIST -> WishlistScreen(
                        wishlistBooks = books.filter { it.isWishlisted },
                        onBookClick = { book -> selectedBookForDetail = book },
                        onToggleWishlist = onToggleWishlist,
                        onExploreClick = { currentDestination = AppDestinations.HOME }
                    )

                    AppDestinations.PROFILE -> ProfileScreen(
                        wishlistCount = books.count { it.isWishlisted }
                    )
                }
            }
        }
    }

    // Modal Bottom Sheet Detail Buku
    selectedBookForDetail?.let { book ->
        ModalBottomSheet(
            onDismissRequest = { selectedBookForDetail = null },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            BookDetailContent(
                book = book,
                onToggleWishlist = { onToggleWishlist(book) },
                onClose = { selectedBookForDetail = null }
            )
        }
    }
}

// --- Screen 1: Katalog Utama (Home) ---
@Composable
fun HomeScreen(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
    onToggleWishlist: (Book) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Semua") }

    val categories = remember(books) {
        listOf("Semua") + books.map { it.category }.distinct()
    }

    val filteredBooks = remember(searchQuery, selectedCategory, books) {
        books.filter { book ->
            val matchesCategory = (selectedCategory == "Semua" || book.category == selectedCategory)
            val matchesSearch = book.title.contains(searchQuery, ignoreCase = true) ||
                    book.author.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Katalog Buku",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Temukan bacaan menarik untuk harimu",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Cari judul atau penulis...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Filter Chips Kategori
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { category ->
                FilterChip(
                    selected = category == selectedCategory,
                    onClick = { selectedCategory = category },
                    label = { Text(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (filteredBooks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Buku tidak ditemukan",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredBooks, key = { it.id }) { book ->
                    BookCard(
                        book = book,
                        onClick = { onBookClick(book) },
                        onToggleWishlist = { onToggleWishlist(book) }
                    )
                }
            }
        }
    }
}

// --- Screen 2: Wish List ---
@Composable
fun WishlistScreen(
    wishlistBooks: List<Book>,
    onBookClick: (Book) -> Unit,
    onToggleWishlist: (Book) -> Unit,
    onExploreClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Wish List Anda",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${wishlistBooks.size} buku tersimpan",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color(0xFFE91E63),
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (wishlistBooks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 60.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Wish List Masih Kosong",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tandai ikon hati pada buku yang ingin Anda baca nanti.",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = onExploreClick) {
                        Text("Jelajahi Katalog")
                    }
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(wishlistBooks, key = { it.id }) { book ->
                    BookCard(
                        book = book,
                        onClick = { onBookClick(book) },
                        onToggleWishlist = { onToggleWishlist(book) }
                    )
                }
            }
        }
    }
}

// --- Screen 3: Profil Pengguna ---
@Composable
fun ProfileScreen(wishlistCount: Int) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))

            // Header Profil
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Avatar",
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Reynaldy Irsyad",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = "reynaldy.pab@student.ac.id",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    AssistChip(
                        onClick = {},
                        label = { Text("Anggota Literasi Premium") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Statistik Pengguna
            Text(
                text = "Statistik Membaca",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                StatCard(
                    title = "Buku Dibaca",
                    value = "14",
                    icon = Icons.Default.Book,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Wish List",
                    value = wishlistCount.toString(),
                    icon = Icons.Default.Favorite,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Menu Pengaturan
            Text(
                text = "Pengaturan & Akun",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    ProfileMenuItem(icon = Icons.Default.Person, title = "Edit Profil")
                    HorizontalDivider()
                    ProfileMenuItem(icon = Icons.Default.Notifications, title = "Notifikasi Bacaan")
                    HorizontalDivider()
                    ProfileMenuItem(icon = Icons.Default.Settings, title = "Pengaturan Aplikasi")
                    HorizontalDivider()
                    ProfileMenuItem(icon = Icons.Default.Info, title = "Tentang Aplikasi Katalog Buku")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline
        )
    }
}

// --- Card Item Buku ---
@Composable
fun BookCard(
    book: Book,
    onClick: () -> Unit,
    onToggleWishlist: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column {
            // Visual Sampul Buku dengan Gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(book.coverGradientStart, book.coverGradientEnd)
                        )
                    )
                    .padding(12.dp)
            ) {
                // Heart Icon Toggle
                IconButton(
                    onClick = onToggleWishlist,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(32.dp)
                        .background(Color.Black.copy(alpha = 0.25f), CircleShape)
                ) {
                    Icon(
                        imageVector = if (book.isWishlisted) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Wishlist",
                        tint = if (book.isWishlisted) Color(0xFFFF4081) else Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Judul singkat di sampul
                Text(
                    text = book.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }

            // Info Buku
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = book.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = book.author,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Rating
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFB300),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = book.rating.toString(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Badge Kategori
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = book.category,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

// --- Content Modal Bottom Sheet Detail Buku ---
@Composable
fun BookDetailContent(
    book: Book,
    onToggleWishlist: () -> Unit,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        // Sampul Buku Besar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(book.coverGradientStart, book.coverGradientEnd)
                    )
                )
                .padding(20.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Surface(
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = book.category,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = book.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = book.author,
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Ringkasan Info (Rating, Halaman, Tahun)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            InfoSpecItem(label = "Rating", value = "★ ${book.rating}")
            InfoSpecItem(label = "Halaman", value = "${book.pages} hlm")
            InfoSpecItem(label = "Tahun", value = book.publishedYear.toString())
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Deskripsi Buku",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = book.description,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Aksi
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = onToggleWishlist,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = if (book.isWishlisted) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (book.isWishlisted) Color(0xFFFF4081) else MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = if (book.isWishlisted) "Tersimpan" else "Wish List")
            }

            Button(
                onClick = onClose,
                modifier = Modifier.weight(1f)
            ) {
                Text("Tutup")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun InfoSpecItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    PAB_TUGAS_W6Theme {
        PAB_TUGAS_W6App()
    }
}
