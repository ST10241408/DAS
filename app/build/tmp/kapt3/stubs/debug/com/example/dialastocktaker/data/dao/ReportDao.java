package com.example.dialastocktaker.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\t\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/example/dialastocktaker/data/dao/ReportDao;", "", "getReportsByJobId", "", "Lcom/example/dialastocktaker/data/entity/Report;", "jobId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReportsByUserId", "userId", "insertReport", "report", "(Lcom/example/dialastocktaker/data/entity/Report;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ReportDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertReport(@org.jetbrains.annotations.NotNull()
    com.example.dialastocktaker.data.entity.Report report, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reports WHERE jobId = :jobId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReportsByJobId(long jobId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dialastocktaker.data.entity.Report>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reports WHERE createdBy = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReportsByUserId(long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dialastocktaker.data.entity.Report>> $completion);
}