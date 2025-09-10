package com.example.dialastocktaker.ui.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0002\u0019\u001aB\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u0010\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\tJ\u0018\u0010\u0017\u001a\u00020\r2\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/dialastocktaker/ui/adapter/JobAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/dialastocktaker/data/model/JobItem;", "Lcom/example/dialastocktaker/ui/adapter/JobAdapter$JobViewHolder;", "Landroid/widget/Filterable;", "()V", "originalList", "", "statusFilter", "", "getFilter", "Landroid/widget/Filter;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setStatusFilter", "status", "submitList", "list", "JobDiffCallback", "JobViewHolder", "app_debug"})
public final class JobAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.dialastocktaker.data.model.JobItem, com.example.dialastocktaker.ui.adapter.JobAdapter.JobViewHolder> implements android.widget.Filterable {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.dialastocktaker.data.model.JobItem> originalList;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String statusFilter;
    
    public JobAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.dialastocktaker.ui.adapter.JobAdapter.JobViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.dialastocktaker.ui.adapter.JobAdapter.JobViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public void submitList(@org.jetbrains.annotations.Nullable()
    java.util.List<com.example.dialastocktaker.data.model.JobItem> list) {
    }
    
    public final void setStatusFilter(@org.jetbrains.annotations.Nullable()
    java.lang.String status) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.widget.Filter getFilter() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/dialastocktaker/ui/adapter/JobAdapter$JobDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/dialastocktaker/data/model/JobItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class JobDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.dialastocktaker.data.model.JobItem> {
        
        public JobDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.dialastocktaker.data.model.JobItem oldItem, @org.jetbrains.annotations.NotNull()
        com.example.dialastocktaker.data.model.JobItem newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.dialastocktaker.data.model.JobItem oldItem, @org.jetbrains.annotations.NotNull()
        com.example.dialastocktaker.data.model.JobItem newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/dialastocktaker/ui/adapter/JobAdapter$JobViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/dialastocktaker/databinding/ItemJobBinding;", "(Lcom/example/dialastocktaker/databinding/ItemJobBinding;)V", "bind", "", "job", "Lcom/example/dialastocktaker/data/model/JobItem;", "app_debug"})
    public static final class JobViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.dialastocktaker.databinding.ItemJobBinding binding = null;
        
        public JobViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.dialastocktaker.databinding.ItemJobBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.dialastocktaker.data.model.JobItem job) {
        }
    }
}