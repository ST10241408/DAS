package com.example.dialastocktaker.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\r\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\u000f\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0015"}, d2 = {"Lcom/example/dialastocktaker/data/dao/JobDao;", "", "getJobById", "Lcom/example/dialastocktaker/data/entity/Job;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJobsByAssignedGroup", "", "groupId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJobsByClientId", "clientId", "getJobsByStatus", "status", "insertJob", "job", "(Lcom/example/dialastocktaker/data/entity/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateJob", "", "app_debug"})
@androidx.room.Dao()
public abstract interface JobDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertJob(@org.jetbrains.annotations.NotNull()
    com.example.dialastocktaker.data.entity.Job job, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateJob(@org.jetbrains.annotations.NotNull()
    com.example.dialastocktaker.data.entity.Job job, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM jobs WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getJobById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.dialastocktaker.data.entity.Job> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM jobs WHERE clientId = :clientId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getJobsByClientId(long clientId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dialastocktaker.data.entity.Job>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM jobs WHERE status = :status")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getJobsByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dialastocktaker.data.entity.Job>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM jobs WHERE assignedGroup = :groupId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getJobsByAssignedGroup(@org.jetbrains.annotations.NotNull()
    java.lang.String groupId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.dialastocktaker.data.entity.Job>> $completion);
}