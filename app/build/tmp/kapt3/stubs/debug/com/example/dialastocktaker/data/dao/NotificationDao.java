package com.example.dialastocktaker.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u0010"}, d2 = {"Lcom/example/dialastocktaker/data/dao/NotificationDao;", "", "getNotificationsByUserId", "", "Lcom/example/dialastocktaker/data/entity/Notification;", "userId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnreadNotificationCount", "", "insertNotification", "notification", "(Lcom/example/dialastocktaker/data/entity/Notification;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAllAsRead", "", "updateNotification", "app_debug"})
@androidx.room.Dao()
public abstract interface NotificationDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertNotification(@org.jetbrains.annotations.NotNull()
    com.example.dialastocktaker.data.entity.Notification notification, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateNotification(@org.jetbrains.annotations.NotNull()
    com.example.dialastocktaker.data.entity.Notification notification, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM notifications WHERE userId = :userId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNotificationsByUserId(long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dialastocktaker.data.entity.Notification>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM notifications WHERE userId = :userId AND isRead = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnreadNotificationCount(long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "UPDATE notifications SET isRead = 1 WHERE userId = :userId AND isRead = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAllAsRead(long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}