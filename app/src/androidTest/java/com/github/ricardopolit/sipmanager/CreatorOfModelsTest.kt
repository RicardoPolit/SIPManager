package com.github.ricardopolit.sipmanager

import com.github.ricardopolit.sipmanager.data.app.App
import com.github.ricardopolit.sipmanager.data.asset.Asset
import com.github.ricardopolit.sipmanager.data.assetbuyfi.AssetBuyFI
import com.github.ricardopolit.sipmanager.data.assetbuyvi.AssetBuyVI
import com.github.ricardopolit.sipmanager.data.assetdividendvi.AssetDividendVI
import com.github.ricardopolit.sipmanager.data.assetsoldvi.AssetSoldVI
import com.github.ricardopolit.sipmanager.data.assetvaluehistory.AssetValueHistory
import com.github.ricardopolit.sipmanager.data.deposit.Deposit
import com.github.ricardopolit.sipmanager.data.portfolio.Portfolio
import com.github.ricardopolit.sipmanager.data.section.Section
import com.github.ricardopolit.sipmanager.util.RandomGenerator
import kotlin.random.Random

object CreatorOfModelsTest{

    private var numberOfPortfolios = 1L
    private var numberOfSections = 1L
    private var numberOfDeposits = 1L
    private var numberOfAssets = 1L
    private var numberOfApps = 1L
    private var numberOfAssetBuyFIs = 1L
    private var numberOfAssetBuyVIs = 1L
    private var numberOfAssetDividendVIs = 1L
    private var numberOfAssetSoldVIs = 1L
    private var numberOfAssetValueHistorys = 1L

    fun makeMultiplePortfolio(quantity: Int): List<Portfolio>{
        val listOfItems = mutableListOf<Portfolio>()
        for(i in 1..quantity)
            listOfItems.add( makeOnePortfolio())

        return listOfItems
    }

    fun makeOnePortfolio(nameWant: String? = null,idPort: Long = -1L): Portfolio{
        val myId = if(idPort!=-1L) idPort else 0L

        return Portfolio(
                id = myId,
                name = nameWant ?: RandomGenerator.getRandomPhrase(2),
                goal = RandomGenerator.getRandomPhrase(4),
                dateFinish = Random.nextLong(1000,1000000),
                totalDeposits = Random.nextFloat() + Random.nextInt(10,1000),
                earnings = Random.nextFloat() + Random.nextInt(10,1000),
                color = RandomGenerator.getRandomPhrase(1, true),
                currency = RandomGenerator.getRandomString(3)
        )

    }

    fun makeMultipleSection(quantity: Int, idPortfolio: Long): List<Section>{
        val listOfItems = mutableListOf<Section>()
        for(i in 1..quantity)
            listOfItems.add( makeOneSection(idPortfolio = idPortfolio))

        return listOfItems
    }

    fun makeOneSection(nameWant: String? = null,idPort: Long = -1L,idPortfolio: Long): Section{
        val myId = if(idPort != -1L) idPort else numberOfSections++

        return Section(
//                id = myId,
            name = nameWant ?: RandomGenerator.getRandomPhrase(2),
            currPercentage = Random.nextFloat() + Random.nextInt(10,1000),
            targetPercentage = Random.nextFloat() + Random.nextInt(10,1000),
            idPortfolio = idPortfolio,
            earnings = Random.nextFloat() + Random.nextInt(10,1000),
            color = RandomGenerator.getRandomPhrase(1, true)
        )

    }

    fun makeMultipleDeposit(quantity: Int, idPortfolio: Long): List<Deposit>{
        val listOfItems = mutableListOf<Deposit>()
        for(i in 1..quantity)
            listOfItems.add( makeOneDeposit(idPortfolio = idPortfolio))

        return listOfItems
    }

    fun makeOneDeposit(idPort: Long = -1L,idPortfolio: Long): Deposit{
        val myId = if(idPort != -1L) idPort else numberOfDeposits++

        return Deposit(
//                id = myId,
            date = Random.nextLong(1,100000),
            amount = Random.nextFloat() + Random.nextInt(0,10000),
            idPortfolio = idPortfolio
            )

    }

    fun makeMultipleAsset(quantity: Int): List<Asset>{
        val listOfItems = mutableListOf<Asset>()
        for(i in 1..quantity)
            listOfItems.add( makeOneAsset())

        return listOfItems
    }

    fun makeOneAsset(nameWant: String? = null,idPort: Long = -1L): Asset{
        val myId = if(idPort != -1L) idPort else numberOfAssets++

        return Asset(
//                id = myId,
            name = nameWant ?: RandomGenerator.getRandomPhrase(2),
            code = RandomGenerator.getRandomPhrase(4),
            currentValue = Random.nextFloat() + Random.nextInt(1,1000),
            color = RandomGenerator.getRandomPhrase(1, true),
            incomeType = Random.nextInt(0,1)
        )

    }

    fun makeMultipleApp(quantity: Int): List<App>{
        val listOfItems = mutableListOf<App>()
        for(i in 1..quantity)
            listOfItems.add( makeOneApp())

        return listOfItems
    }

    fun makeOneApp(nameWant: String? = null,idPort: Long = -1L, idAssetDefault: Long = -1): App{
        val myId = if(idPort != -1L) idPort else numberOfApps++

        return App(
//                id = myId,
            name = nameWant ?: RandomGenerator.getRandomPhrase(2),
            idAssetDefault = idAssetDefault

        )

    }

    fun makeMultipleAssetBuyFI(quantity: Int, idAsset: Long): List<AssetBuyFI>{
        val listOfItems = mutableListOf<AssetBuyFI>()
        for(i in 1..quantity)
            listOfItems.add( makeOneAssetBuyFI(idAsset = idAsset))

        return listOfItems
    }

    fun makeOneAssetBuyFI(nameWant: String? = null,idPort: Long = -1L, idAsset: Long): AssetBuyFI{
        val myId = if(idPort != -1L) idPort else numberOfAssetBuyFIs++
        val myDateBought = Random.nextLong(10000000)
        return AssetBuyFI(
//                id = myId,
            idAsset = idAsset,
            fixedIncome = Random.nextFloat(),
            valueBought = Random.nextFloat() + Random.nextInt(100),
            quantity = Random.nextInt(1,50).toFloat(),
            dateBought = myDateBought,
            dateFinish = myDateBought + Random.nextLong(10000000)
        )

    }

    fun makeMultipleAssetBuyVI(quantity: Int, idAsset: Long): List<AssetBuyVI>{
        val listOfItems = mutableListOf<AssetBuyVI>()
        for(i in 1..quantity)
            listOfItems.add( makeOneAssetBuyVI(idAsset = idAsset))

        return listOfItems
    }

    fun makeOneAssetBuyVI(nameWant: String? = null,idPort: Long = -1L, idAsset: Long): AssetBuyVI{
        val myId = if(idPort != -1L) idPort else numberOfAssetBuyVIs++
        val myDateBought = Random.nextLong(10000000)
        return AssetBuyVI(
//                id = myId,
            idAsset = idAsset,
            valueBought = Random.nextFloat() + Random.nextInt(100),
            quantity = Random.nextInt(1,50).toFloat(),
            dateBought = myDateBought
        )

    }

    fun makeMultipleAssetDividendVI(quantity: Int, idAsset: Long): List<AssetDividendVI>{
        val listOfItems = mutableListOf<AssetDividendVI>()
        for(i in 1..quantity)
            listOfItems.add( makeOneAssetDividendVI(idAsset = idAsset))

        return listOfItems
    }

    fun makeOneAssetDividendVI(nameWant: String? = null,idPort: Long = -1L, idAsset: Long): AssetDividendVI{
        val myId = if(idPort != -1L) idPort else numberOfAssetDividendVIs++
        val myDateReturned = Random.nextLong(10000000)
        return AssetDividendVI(
//                id = myId,
            idAsset = idAsset,
            figureReturned = Random.nextFloat() + Random.nextInt(10),
            quantity = Random.nextInt(1,50).toFloat(),
            dateReturned = myDateReturned
        )

    }

    fun makeMultipleAssetSoldVI(quantity: Int, idAsset: Long): List<AssetSoldVI>{
        val listOfItems = mutableListOf<AssetSoldVI>()
        for(i in 1..quantity)
            listOfItems.add( makeOneAssetSoldVI(idAsset = idAsset))

        return listOfItems
    }

    fun makeOneAssetSoldVI(nameWant: String? = null,idPort: Long = -1L, idAsset: Long): AssetSoldVI{
        val myId = if(idPort != -1L) idPort else numberOfAssetSoldVIs++
        val myDateSold = Random.nextLong(10000000)
        return AssetSoldVI(
//                id = myId,
            idAsset = idAsset,
            valueSold = Random.nextFloat() + Random.nextInt(100),
            quantity = Random.nextInt(1,50).toFloat(),
            dateSold = myDateSold,
            earnings = Random.nextFloat() + Random.nextInt(10000)
        )

    }

    fun makeMultipleValueHistory(quantity: Int, idAsset: Long): List<AssetValueHistory>{
        val listOfItems = mutableListOf<AssetValueHistory>()
        for(i in 1..quantity)
            listOfItems.add( makeOneValueHistory(idAsset = idAsset))

        return listOfItems
    }

    fun makeOneValueHistory(nameWant: String? = null,idPort: Long = -1L, idAsset: Long): AssetValueHistory{
        val myId = if(idPort != -1L) idPort else numberOfAssetValueHistorys++
        val myDate = Random.nextLong(10000000)
        return AssetValueHistory(
//                id = myId,
            idAsset = idAsset,
            value = Random.nextFloat() + Random.nextInt(100),
            date = myDate
        )

    }

}