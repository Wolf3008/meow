DynamicItems = function (collectionRequest, getFilter, pageSize) {
    this.getEntities = collectionRequest;
    this.getFilter = getFilter;
        /**
         * @type {!Object<?Array>} Data pages, keyed by page number (0-index).
         */
        this.loadedPages = {};
        /** @type {number} Total number of items. */
        this.numItems = 0;
        /** @const {number} Number of items to fetch per request. */
        this.PAGE_SIZE = pageSize||35;
        this.clientsListNeedRefresh = false;
        this.lastIndex = 0;
        this.fetchNumItems_();
//        this.fetchPage_(1);
        
    };
    // Required.
    DynamicItems.prototype.getItemAtIndex = function (index) {
        var pageNumber = Math.floor(index / this.PAGE_SIZE);
        var page = this.loadedPages[pageNumber];
        if (page&&!this.clientsListNeedRefresh) {
            return page[index % this.PAGE_SIZE];
        } else if (page !== null) {
            this.fetchPage_(pageNumber);
        }
    };
    // Required.
    DynamicItems.prototype.getLength = function () {
        return this.numItems;
    };
    DynamicItems.prototype.fetchPage_ = function (pageNumber) {
        // Set the page to null so we know it is already being fetched.
        this.loadedPages[pageNumber] = null;
        
        var s = this;
        this.getEntities(this.getFilter(pageNumber+1, this.PAGE_SIZE)).success(function (data){
            s.loadedPages[pageNumber] = data.rows;
            s.numItems = data.count;
            s.clientsListNeedRefresh = false;
            console.log(s);
        });
    };
    DynamicItems.prototype.fetchNumItems_ = function () {
        
        var s = this;
        this.getEntities(this.getFilter(1,1)).success(function (data){
            //s.loadedPages[1] = data.rows;
            s.numItems = data.count;
        });
    };