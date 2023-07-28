package main;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        // Initialize the game panel for the collision checker
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // Calculate the position of the entity's solid area in the world coordinates
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // Calculate the indices of the tiles that intersect with the entity's solid area
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        // Check collision based on the entity's current direction
        switch (entity.direction) {
            case "up":
                // Move the top row up by one tile and check the two tiles on the left and right of the entity
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                checkCollision(entityLeftCol, entityTopRow, entity);
                checkCollision(entityRightCol, entityTopRow, entity);
                break;

            case "down":
                // Move the bottom row down by one tile and check the two tiles on the left and right of the entity
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                checkCollision(entityLeftCol, entityBottomRow, entity);
                checkCollision(entityRightCol, entityBottomRow, entity);
                break;

            case "left":
                // Move the left column to the left by one tile and check the two tiles above and below the entity
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                checkCollision(entityLeftCol, entityTopRow, entity);
                checkCollision(entityLeftCol, entityBottomRow, entity);
                break;

            case "right":
                // Move the right column to the right by one tile and check the two tiles above and below the entity
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                checkCollision(entityRightCol, entityTopRow, entity);
                checkCollision(entityRightCol, entityBottomRow, entity);
                break;
        }
    }

    // Check if the given tile can be collided with and set the entity's collisionOn flag accordingly
    private void checkCollision(int col, int row, Entity entity) {
        // Ensure that the given tile is within the bounds of the tile map
        if (col >= 0 && col < gp.tileM.mapTileNum.length && row >= 0 && row < gp.tileM.mapTileNum[0].length) {
            int tileNum = gp.tileM.mapTileNum[col][row];
            if (gp.tileM.tile[tileNum].collision) {
                entity.collisionOn = true;
            }
        }
    }

	
    public int checkObjectCollision(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                // get entity and object solid area positions
                entity.solidArea.x = entity.worldX + entity.solidArea.x; 
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                // check for collision in all directions
                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                    case "up_right":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        break;
                    case "up_left":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "down_right":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        break;
                    case "down_left":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        break;
                }
                
                // check for collision with object and update entity collision status and index if player is true
                if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                    if(gp.obj[i].collision) {
                        entity.collisionOn = true;
                    }
                    if(player) {
                        index = i;
                    }
                }
                
                // reset solid area positions to their default values
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        
        return index;	
    }
}