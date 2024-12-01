package com.nevrmd.barkbark.data.model

data class Breed(
    val id: String,
    val type: String,
    val attributes: BreedAttributes,
    val relationships: BreedRelationships
)

// Attributes
data class BreedAttributes(
    val name: String,
    val description: String,
    val life: BreedLife,
    val maleWeight: BreedMaleWeight,
    val femaleWeight: BreedFemaleWeight,
    val hypoallergenic: Boolean,
)

data class BreedFemaleWeight(
    val min: Int,
    val max: Int,
)

data class BreedMaleWeight(
    val min: Int,
    val max: Int,
)

data class BreedLife(
    val min: Int,
    val max: Int,
)

// Relationships
data class BreedRelationships(
    val group: BreedGroup,
)

data class BreedGroup(
    val data: BreedGroupData,
)

data class BreedGroupData(
    val id: String,
    val type: String,
)
