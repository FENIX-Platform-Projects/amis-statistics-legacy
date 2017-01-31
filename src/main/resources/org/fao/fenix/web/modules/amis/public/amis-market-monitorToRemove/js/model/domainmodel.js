function MarketMonitorDomainModel(domainname, domaincode, domaintablecode, domainsource, domainindicators, indicatormarkets, domainserverscriptname) {
    this._domainName = domainname;
    this._domainCode = domaincode;    
    this._domainTableCode = domaintablecode;
    this._domainIndicators = domainindicators;
    this._indicatorMarkets = indicatormarkets;
    this._domainServerScriptName = domainserverscriptname;
    this._domainSource = domainsource;
  
}

MarketMonitorDomainModel.prototype = {
    getDomainName : function () {
        return this._domainName;
    },
    
    getDomainCode : function () {
        return this._domainCode;
    },

    getDomainIndicators : function () {
        return [].concat(this._domainIndicators);
        
    },
    
    getIndicatorMarkets : function () {
            if(this._indicatorMarkets==null)
              return null;
            else 
               return [].concat(this._indicatorMarkets);     
    },
    
     getDomainTableCode : function () {
         return this._domainTableCode;
    },

    getDomainSource : function () {
        return this._domainSource;
    },

     getDomainServerScriptName : function () {
         return this._domainServerScriptName;
    }
};
